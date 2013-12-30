import json

from base import Base

class Share(Base):
  name = 'share'
  page_size = 100

  def _normalize(self, responses):
    res = []
    for item in responses:
      share = {}
      share['time'] = item['shareTime']
      share['text'] = item['title']
      share['comment'] = item['commentCount']
      share['url'] = item['url']
      res.append(json.dumps(share)+'\n')
    return res

  def _get_time(self, lines):
    if lines:
      return json.loads(lines[0])['time']
