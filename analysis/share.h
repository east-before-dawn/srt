#include <iostream>
#include "sys/types.h"
#include "dirent.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <ctime>
using namespace std;

void get_share_info(const char * path) {
  char file_name[100];
  memset(file_name, 0, sizeof(file_name));
  strcpy(file_name, path);
  strncat(file_name, "/share.dat", sizeof("/share.dat"));
  freopen(file_name, "r", stdin);

  int num = 0;
  int commentsum = 0;
  int photosum = 0;
  int blogsum = 0;
  int othersum = 0;
  int videosum = 0;
  long timediff = 0;

  string url, title;
  struct tm Time, Time1;
  // Get title
  while (getline(cin, title)) {
    // Get url
    getline(cin, url);
    if (url.find("photo") == 7)
      photosum ++;
    else if (url.find("blog") == 7)
      blogsum ++;
    else {
      if (url.find("youku") != string::npos ||
          url.find("tudou") != string::npos ||
             url.find("56") != string::npos ||
          url.find("youtube") != string::npos)
        videosum ++;
    }
    
    int i1;
    scanf("%d ", &i1);
    // Get comment count
    commentsum += i1;

    // Get time
    scanf("%d-%d-%d %d:%d:%d ", &Time.tm_year, &Time.tm_mon, &Time.tm_mday, &Time.tm_hour, &Time.tm_min, &Time.tm_sec);
    
    // Get time diff(with last status)
    if (num) {
      int difft = difftime(mktime(&Time1), mktime(&Time));
      timediff += difft;
    }
    Time1 = Time;

    // Get share count
    num++;

  }
  // num
  // photosum / num
  // blogsum / num
  // videosum / num
  // othersum / num
  // commentsum / sum
  // timediff / (num - 1)
  // **num <= 1**
  return;
}
