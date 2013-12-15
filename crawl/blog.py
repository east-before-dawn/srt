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
      blog = {}
      blog['share'] = str(item['shareCount'])
      blog['time'] = item['createTime']
      blog['text'] = item['content']
      blog['title'] = item['title']
      blog['view'] = str(item['viewCount'])
      blog['textlength'] = str(len(item['content']))
      blog['comment'] = str(item['commentCount'])
      blog['access'] = access_control_map(item['accessControl'])
      blog['type'] = type_map[item['type']]
      res.append(json.dumps(blog)+'\n')
    return res

  def _get_time(self, lines):
    if lines is not None and len(lines) > 3:
      return lines[3][:-1]
