from fastapi import Body, FastAPI, Request, HTTPException
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
from requests.exceptions import HTTPError
from datetime import datetime, timedelta
from typing import *
import requests
import json
import asyncio
import pytz
from env import SECRET_API_KEY
from secrets import TOMMOROW_API_KEY, OPENWEATHER_API_KEY


app=FastAPI()

with open("city_locations.json", "r") as file:
    cities_data = json.loads(file.read())

templates = Jinja2Templates(directory="templates")

@app.exception_handler(HTTPException)
async def http_exception_handler(request, exc):
    return templates.TemplateResponse("error.html", {"request": request, "error_message": exc.detail})

@app.get("/form", response_class=HTMLResponse)
async def get_form_page(request: Request):
    return templates.TemplateResponse("form.html", {"request": request})

def get_weather(city):
    city_latitute = cities_data[city]["lat"]
    city_longitude = cities_data[city]["lon"]
    open_url = f'https://api.openweathermap.org/data/2.5/forecast?lat={city_latitute}&lon={city_longitude}&units=metric&appid={OPENWEATHER_API_KEY}'
    open_weather = asyncio.to_thread(requests.get, open_url)
    tommorow_url = f"https://api.tomorrow.io/v4/weather/forecast?location={city_latitute}, {city_longitude}&apikey={TOMMOROW_API_KEY}"
    tommorow = asyncio.to_thread(requests.get, tommorow_url)
    return open_weather, tommorow

def get_current_time():
    return datetime.now(pytz.utc)

def get_current_time_plus(hours: int) -> datetime:
    return get_current_time() + timedelta(hours=hours)

def parse_openweather(forecast, start: datetime, end: datetime) -> int:
    date_str = "%Y-%m-%d %H:%M:%S"
    entry_count = 0
    avg_temp = 0
    max_temp = -100
    min_temp = 100
    for entry in forecast["list"]:
        entry_date = datetime.strptime(entry["dt_txt"], date_str)
        entry_date = pytz.utc.localize(entry_date)
        if start <  entry_date and entry_date < end:
            cur_temp = entry["main"]["temp"]
            avg_temp += cur_temp
            entry_count += 1
            max_temp = max(max_temp, cur_temp)
            min_temp = min(min_temp, cur_temp)
    return avg_temp / entry_count, min_temp, max_temp

def parse_tommorow(forecast, start: datetime, end: datetime) -> int:
    date_str = "%Y-%m-%dT%H:%M:%SZ"
    avg_temp = 0
    entry_count = 0
    max_temp = -100
    min_temp = 100
    for entry in forecast["timelines"]["hourly"]:
        entry_date = datetime.strptime(entry["time"], date_str)
        entry_date = pytz.utc.localize(entry_date)
        if start <  entry_date and entry_date < end:
            cur_temp = entry["values"]["temperature"]
            avg_temp += cur_temp
            entry_count += 1
            max_temp = max(max_temp, cur_temp)
            min_temp = min(min_temp, cur_temp)
    return avg_temp / entry_count, min_temp, max_temp

@app.get("/raport", response_class=HTMLResponse)
async def get_form_page(request: Request, start: str, end: str, key: str):
    try:
        start = int(start)
        end = int(end)
    except ValueError:
        return templates.TemplateResponse("error.html", {"request": request, "error_code": 400, "error_message": "Bad Request: start and end must be integers"})
    if abs(end - start) < 3:
        return templates.TemplateResponse("error.html", {"request": request, "error_code": 400, "error_message": "Bad Request: start and end must be at least 3 hours apart"})
    elif start > end or start < 0 or end > 120:
        return templates.TemplateResponse("error.html", {"request": request, "error_code": 400, "error_message": "Bad Request: start must be smaller than end and both must be between 0 and 120"})
    if key != SECRET_API_KEY:
        return templates.TemplateResponse("error.html", {"request": request, "error_code": 401, "error_message": "Unauthorized"})

    start_time = get_current_time_plus(hours=start)
    end_time = get_current_time_plus(hours=end)

    promises = {}
    for city in cities_data:
        promises[city] = get_weather(city)

    context = {"request": request, "start_date": start_time, "end_date": end_time}
    global_avg = 0
    for idx, (city, (open_result, tommorow_result)) in enumerate(promises.items()):
        tommorow_result = await tommorow_result
        open_result = await open_result
        try:
            tommorow_result.raise_for_status()
        except HTTPError as http_err:
            return templates.TemplateResponse("error.html", {"request": request, "error_code": tommorow_result.status_code, "error_message": f"tommorow API: {tommorow_result.reason}"})
        try:
            open_result.raise_for_status()
        except HTTPError as http_err:
            return templates.TemplateResponse("error.html", {"request": request, "error_code": open_result.status_code, "error_message": f"open weather API: {open_result.reason}"})

        avg_open, min_open, max_open = parse_tommorow(tommorow_result.json(), start_time, end_time)
        avg_tommorow, min_tommorow, max_tommorow = parse_openweather(open_result.json(), start_time, end_time)
        avg = (avg_open + avg_tommorow) / 2
        global_avg += avg
        min_temp = min(min_open, min_tommorow)
        max_temp = max(max_open, max_tommorow)
        context[f'city_name_{idx}'] = city
        context[f'avg_temp_{idx}'] = round(avg, 2)
        context[f'min_temp_{idx}'] = min_temp
        context[f'max_temp_{idx}'] = max_temp
    context["avg_temp"] = round(global_avg / len(cities_data), 2)
    return templates.TemplateResponse("raport.html", context)
