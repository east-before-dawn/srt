import json

from common import get
from setting import url

class Profile(object):
  name = 'profile'

  def _crawl(self, token_list, user_id, until_time=None):
    token = token_list[-1]
    response = json.loads(get(url + 'profile/get', [
      ('access_token', token),
      ('userId', user_id),
    ]))
    if 'response' not in response:
      return None
    response.update(json.loads(get(url + 'user/get', [
      ('access_token', token),
      ('userId', user_id),
    ])))
    return response['response']

  def _normalize(self, response):
    return [
        response['name'],
        response['star'],
        response['basicInformation'],
        response['education'],
        response['work'],
        response['like'],
        response['appCount'],
        response['visitorCount'],
        response['pageCount'],
        response['zhancount'],
        response['musicCount'],
        response['movieCount'],
        response['friendCount'],
        #response['density'],
    ]

  def _get_time(self, lines):
    return None
