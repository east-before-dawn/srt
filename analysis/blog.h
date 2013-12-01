#include <iostream>
#include "sys/types.h"
#include "dirent.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <ctime>
using namespace std;

void get_blog_info(const char * path) {
  char file_name[100];
  memset(file_name, 0, sizeof(file_name));
  strcpy(file_name, path);
  strncat(file_name, "/blog.dat", sizeof("/blog.dat"));
  freopen(file_name, "r", stdin);

  int num = 0;
  int sharesum = 0;
  int commentsum = 0;
  int accesssum = 0;
  int viewsum = 0;
  int typesum = 0;
  long timediff = 0;
  long textlong = 0;

  string title;
  struct tm Time, Time1;
  // Get Title
  while (getline(cin, title)) {
    int i1, i2, i3, i4;
    scanf("%d %d", &i1, &i2);
    
    // Get content length
    textlong += i1;

    // Get Type rate
    typesum += i2;

    // Get time
    scanf("%d-%d-%d %d:%d:%d:000 ", &Time.tm_year, &Time.tm_mon, &Time.tm_mday, &Time.tm_hour, &Time.tm_min, &Time.tm_sec);
    
    // Get time diff`:w(with last status)
    if (num) {
      int difft = difftime(mktime(&Time1), mktime(&Time));
      timediff += difft;
    }
    Time1 = Time;

    scanf("%d %d %d %d ", &i1, &i2, &i3, &i4);

    // Get status count
    num++;

    // Get share count
    sharesum += i1;

    // Get access count
    accesssum += 1 - i2;

    // Get comment count
    commentsum += i4;

    // Get view count
    viewsum += i3;

  }
  // num
  // sharesum / num
  // commentsum / num
  // viewsum / num
  // textlong / num
  // typesum / num
  // accesssum / num
  // timediff / (num - 1)
  // **num <= 1**
  return;
}
