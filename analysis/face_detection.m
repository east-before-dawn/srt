function [ans] = face_detection(filename);

close all

I = imread(filename);

[Ix, Iy, Iz] = size(I);
I = imresize(I, [400, Iy * 400 / Ix], 'nearest');
%figure
%imshow(I)
%title('normal image')

%skin detection 
I = double(I);             
[hue, s, v] = rgb2hsv(I);   
if (Iz >=3) 
    cb = 0.148 * I(:,:,1) - 0.291 * I(:,:,2) + 0.439 * I(:,:,3) + 128;
    cr=0.439 * I(:,:,1) - 0.368 * I(:,:,2) - 0.071 * I(:,:,3) + 128;  
else if (Iz >=2) 
     cb = 0.148 * I(:,:,1) - 0.291 * I(:,:,2) + 128;
    cr=0.439 * I(:,:,1) - 0.368 * I(:,:,2) + 128;      
else
        cb = 0.148 * I(:,:,1) ;
    cr=0.439 * I(:,:,1);   
    end
end
[w h] = size(I(:,:,1));

for i = 1 : w
    for j = 1 : h     
        %if  145 <= cr(i, j) & cr(i, j) <= 165 & 145 <= cb(i, j) & cb(i, j) <= 180 & 0.01 <= hue(i, j) & hue(i, j) <= 0.15    
        if 130 <= cr(i,j) & cr(i,j) <= 190 & 145 <= cb(i,j) & cb(i,j) <= 180 & 0.01 <= hue(i,j) & hue(i,j) <= 0.15
            segment(i,j) = 1; %skin areas        
        else       
            segment(i,j) = 0;    
        end    
    end
end
%figure
%imshow(segment);

skin = segment;
skin = bwareaopen(skin, round(w * h / 900));
se = strel('disk', 5);
skin = imdilate(skin, se);        


BW = skin;
L = bwlabel(BW,8);

BB  = regionprops(L, 'BoundingBox');
BB1 = struct2cell(BB);
%saving the begining point of connected frame and its high and wide
BB2 = cell2mat(BB1);
%figure, imshow(uint8(I));
%title('result image');

[s1 s2] = size(BB2);
ans = 0;
for k = 3:4:s2-1
    if (BB2(1, k) / BB2(1, k + 1)) < 1.2 &&....
        (BB2(1, k) / BB2(1, k + 1)) > 0.6 &&....
         (BB2(1, k) * BB2(1, k + 1)) > 100
     %hold on;
     ans = ans + 1;
     %rectangle('Position',[BB2(1,k-2),BB2(1,k-1),BB2(1,k),BB2(1,k+1)],'EdgeColor','r' )
    end
end

end


