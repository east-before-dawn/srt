import json

from base import Base

class Status(Base):
  name = 'status'
  page_size = 100

  def _normalize(self, responses):
    res = []
    for item in responses:
      status = {}
      status['share'] = str(item['shareCount'])
      status['time'] = item['createTime']
      status['text'] = item['content']
      status['comment'] = str(item['commentCount'])
      status['sharedID'] = str(item['sharedStatusId'])
      res.append(json.dumps(status)+'\n')
    return res

  def _get_time(self, lines):
    if lines is not None and len(lines) > 1:
      return lines[1][:-1]
