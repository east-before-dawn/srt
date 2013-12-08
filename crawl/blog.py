import json

from base import Base

__all__ = ['Blog']

type_map = {
  'TYPE_DEFAULT':'0',
  'TYPE_WAP':'1',
  'TYPE_RSS':'2',
  'TYPE_API':'3',
  'TYPE_OTHER':'4',
}

def access_control_map(access_control):
  if access_control == 'PUBLIC':
    return '0'
  return '1'

class Blog(Base):
  name = 'blog'

  def _normalize(self, responses):
    res = []
    for item in responses:
      res.extend((
        item['title'] + '\n',
        str(len(item['content'])) + '\n',
        item['content'] + '\n',
        type_map[item['type']] + '\n',
        item['createTime'] + '\n',
        str(item['shareCount']) + '\n',
        access_control_map(item['accessControl']) + '\n',
        str(item['viewCount']) + '\n',
        str(item['commentCount']) + '\n',
      ))
    return res

  def _get_time(self, lines):
    if lines is not None and len(lines) > 3:
      return lines[3][:-1]
