from fastapi import Body, FastAPI, status
from fastapi.responses import JSONResponse
from enum import Enum
from pydantic import BaseModel
from typing import *

app=FastAPI( )

class Votes(BaseModel):
    value: str
    id: str

class Poll():
    def __init__(self, question: str, votes: List[Votes] = []):
        self.question = question
        self.votes = votes

polls = {}


@app.get("/poll")
async def get_polls_list():
    return polls

@app.post("/poll/")
async def create_poll(poll_name: str):
    if poll_name in polls:
            return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content={"message": "Poll exists already"})
    polls[poll_name] = Poll(question=poll_name)
    return "Poll created"


@app.get("/poll/{p_id}")
async def return_poll_result(p_id: str):
    return p_id

@app.put("/poll/{p_id}")
async def edit_poll():
    return

@app.delete("/poll/{p_id}")
async def delete_poll():
    return


@app.get("/poll/{p_id}/vote")
async def get_votes_from_poll():
    return

@app.post("/poll/{p_id}/vote")
async def add_vote_to_poll():
    return


@app.get("/poll/{p_id}/vote/{v_id}")
async def get_vote_information():
    return

@app.put("/poll/{p_id}/vote/{v_id}")
async def update_vote():
    return

@app.delete("/poll/{p_id}/vote/{v_id}")
async def delete_vote():
    return