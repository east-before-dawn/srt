#include <iostream>
#include "sys/types.h"
#include "dirent.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <ctime>
using namespace std;

void get_status_info(const char * path) {
  char file_name[100];
  memset(file_name, 0, sizeof(file_name));
  strcpy(file_name, path);
  strncat(file_name, "/status.dat", sizeof("/status.dat"));
  freopen(file_name, "r", stdin);

  int num = 0;
  int sharesum = 0;
  int commentsum = 0;
  int sharedsum = 0;
  long timediff = 0;
  long textlong = 0;

  string content;
  struct tm Time, Time1;
  // Get status content
  while (getline(cin, content)) {
    // Get time
    scanf("%d-%d-%d %d:%d:%d ", &Time.tm_year, &Time.tm_mon, &Time.tm_mday, &Time.tm_hour, &Time.tm_min, &Time.tm_sec);
    
    // Get time diff(with last status)
    if (num) {
      int difft = difftime(mktime(&Time1), mktime(&Time));
      timediff += difft;
    }
    Time1 = Time;

    int i1, i2, i3;
    scanf("%d %d %d ", &i1, &i2, &i3);

    // Get status count
    num++;

    // Get share count
    sharesum += i1;

    // Get comment count
    commentsum += i2;

    // shared status?
    if (i3) sharedsum ++;

    // text length
    textlong += content.size();
  }
  // num
  // sharesum / num
  // commentsum / num
  // sharedsum / num
  // textlong / num
  // timediff / (num - 1)
  return;
}
