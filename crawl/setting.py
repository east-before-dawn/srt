__all__ = [
  'base_token',
  'interval',
  'user_list_path',
  'data_path',
  'url',
  'urlopen_timeout',
  'urlopen_try_times',
]

token_list = [
  '244215|6.c7bbef98d48b195b5a92f8dc71603d99.2592000.1389006000-287478772',
  '245149|6.b183bee65165c557d8c3f7de3ddd3aff.2592000.1389092400-403722801',
  '244214|6.43e90a737d9cc13e4162a5828b643eef.2592000.1389096000-250062710',
  '244281|6.9b10f0399300fd3d108f22957a48dcf4.2592000.1389096000-250062710',
]

interval = 3600*24*2

user_list_path = '../server/server/data/'
data_path = '../analysis/data_json/'
img_data_path = '../analysis/data_img/'

url = 'https://api.renren.com/v2/'

urlopen_timeout = 20
urlopen_try_times = 10
