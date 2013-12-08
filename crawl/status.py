import json

from base import Base

class Status(Base):
  name = 'status'
  page_size = 100

  def _normalize(self, responses):
    res = []
    for item in responses:
      res.extend((
        item['content'] + '\n',
        item['createTime'] + '\n',
        str(item['shareCount']) + '\n',
        str(item['commentCount']) + '\n',
        str(item['sharedStatusId']) + '\n',
      ))
    return res

  def _get_time(self, lines):
    if lines is not None and len(lines) > 1:
      return lines[1][:-1]
