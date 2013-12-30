import cv
import math

__all__ = ['draw_it']

def draw_it(scores, width=800, height=600, radiu=200, filename='result.png'):
  image = cv.CreateImage((width, height), 8, 3)

  text = ["O", "C", "E", "A", "N"]

  # background color
  cv.Rectangle(image, (0, 0), (width, height), (255, 255, 255), -1)

  # center and angle
  cx = width * 0.5
  cy = height * 0.5
  angle = math.pi * 0.4

  # draw outer pentagon
  pt = []
  for i in range(0, 5):
    pt.append((int(cx+radiu*math.sin(angle*i)),
      int(cy-radiu*math.cos(angle*i))))
  cv.FillConvexPoly(image, pt, (200,200,200))

  # draw inner polygon
  pt = []
  for i, t in enumerate(text):
    pt.append((int(cx+scores[t]*radiu*math.sin(angle*i)),
      int(cy-scores[t]*radiu*math.cos(angle*i))))
  cv.FillConvexPoly(image, pt, (100,100,100))

  # draw lines
  colors = [(0x33,0x33,0xFF), (0x33,0xFF,0xFF), (0x33,0xFF,0x66),
      (0xCC,0x66,0x00), (0xCC,0x00,0x66)]
  for i in range(0, 5):
    cv.Line(image, (int(cx), int(cy)), (int(cx+radiu*math.sin(angle*i)),
      int(cy-radiu*math.cos(angle*i))), colors[i], 2)

  # draw text
  font = cv.InitFont(cv.CV_FONT_HERSHEY_SIMPLEX, 1, 1, 0, 3, 8)
  for i, t in enumerate(text):
    cv.PutText(image, t, (int(cx+(radiu+20)*math.sin(angle*i))-10,
      int(cy-(radiu+20)*math.cos(angle*i))+10), font, (0,0,0))

  cv.SaveImage(filename, image)