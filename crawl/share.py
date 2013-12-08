import json

from base import Base

class Share(Base):
  name = 'share'
  page_size = 100

  def _normalize(self, responses):
    res = []
    for item in responses:
      res.extend((
        item['title'] + '\n',
        item['url'] + '\n',
        item['commentCount'] + '\n',
        item['shareTime'] + '\n',
      ))
    return res

  def _get_time(self, lines):
    if lines is not None:
      return lines[3][:-1]
