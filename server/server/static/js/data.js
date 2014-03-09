data = [
        {
        "title": "1",
        "description": "Q1: 我觉得我很健谈",
        "options":[
                   "强烈反对",
                   "反对",
                   "一般",
                   "同意",
                   "强烈同意",
                   ],
        "long": true,
        "sol": [0,1,2,3,4],
        },
        {
        "title": "Napping",
        "description": "Did you take a nap today?",
        "options":[
                   "Yes",
                   "No",
                   ],
        "good": "Awesome. Sometimes napping in the day makes it harder to go to bed at night.",
        "bad": "Sometimes napping in the day makes it harder to go to bed at night.",
        "sol": [1],
        "suggestion": "Avoid napping in the daytime if you have trouble falling asleep at your usual bedtime."
        },
        
        {
        "title": "Worrying",
        "description": "Are you worried about something right now?",
        "options":[
                   "A lot",
                   "A little",
                   "Not at all",
                   ],
        "good": "That's good to hear!",
        "bad": "Worrying can make falling asleep harder.",
        "sol": [2],
        "suggestion": "Get your worrying completely out of the way at least an hour or two before your bedtime. It is sometimes very helpful to schedule a specific \"worry time\"."
        },
        
        {
        "title": "Meals",
        "description": "When was your last meal and how big was it?",
        "options":[
                   "I had a big meal several hours ago.",
                   "I had a big meal quite recently.",
                   "I had a small meal several hours ago.",
                   "I had a small meal quite recently."
                   ],
        "long": true,
        "good": "Great! Avoid eating big meals right before you sleep.",
        "bad": "Oops! A heavy meal right before bed might not be the best idea.",
        "sol": [0, 2, 3],
        "suggestion": "Avoid head meals before bedtime; a light snack may be desirable."
        },
        
        {
        "title": "Caffeine/nicotine",
        "description": "When was the last time you had caffeinated beverages (coffee, tea, etc.) or smoked today?",
        "options":[
                   "More than 6 hours ago.",
                   "Less than 6 hours ago.",
                   "I didn't do either today.",
                   ],
        "long": true,
        "good": "Excellent! Caffeine and nicotine can both interfere with sleep schedule.",
        "bad": "Unfortunately, caffeine and nicotine can both interfere with sleep schedule.",
        "sol": [0, 2],
        "suggestion": "Avoid all caffeinated beverages and food (e.g. chocalate) at least 6 hours before you bedtime. "
        + "If you smoke, smoke less in the evening and make a serious effort to entirely quit."
        },
        
        {
        "title": "Excitement",
        "description": "Did you do any of the following things just now?",
        "options":[
                   "I just read a super exciting book!",
                   "I just watched an episode of TV that kept my heart pumping.",
                   "I just went to a concert and I can still feel the excitement!",
                   "I did something else that's just as exciting!",
                   "Nope. The past few hours have been pretty calm."
                   ],
        "long": true,
        "good": "Excellent! Excitement is an essential part of life, but not right before bed :)",
        "bad": "Too much excitement going on right now... Hard to sleep!",
        "sol": [4],
        "suggestion": "Establish a relaxing, soporific routine leading up to your bedtime. This might include a warm bath, "
        + "which many people find improve their sleep. Avoid exciting reading material right before sleep."
        },
        
        {
        "title": "Sleep Routine",
        "description": "What time is it now compared to your usual bedtime?",
        "options":[
                   "It is earlier than my usual bedtime.",
                   "It is around my usual bedtime.",
                   "It is later than my usual bedtime."
                   ],
        "long": true,
        "good": "Great! A regular bedtime helps you fulfill your daily sleep requirement.",
        "bad": "A regular bedtime helps you fulfill your daily sleep requirement.",
        "sol": [1],
        "suggestion": "Establish a regular bedtime and a regular getting up time that allows you to fulfill your "
        + "daily sleep requirement. Stick to the schedule within reason."
        },
        
        {
        "title": "Noise",
        "description": "Is it loud in your room?",
        "options":[
                   "My roommate is snoring.",
                   "Construction outside!",
                   "There's other type of noise going on.",
                   "Nope. Very quiet.",
                   ],
        "long": true,
        "good": "Great! Even better, try playing a soothing sound when going to bed.",
        "bad": "Sleep needs to be priority for all. Try to find a quieter environment :)",
        "sol": [3],
        "suggestion": "Try to have your sleeping area as quiet as possible. If it is helpful a soothing sound, like the ocean, can be played."
        },
        
        {
        "title": "Light",
        "description": "Is it bright in your room?",
        "options":[
                   "Yes",
                   "No",
                   ],
        "good": "Great! Darkness induces sleep.",
        "bad": "Some people have a hard time falling asleep when it's bright. If that's an issue, try wearing a sleep mask.",
        "sol": [1],
        "suggestion": "Try to have your sleeping area as dark as possible."
        },
        
        {
        "title": "Comfort",
        "description": "Is your bed comfortable enough for you?",
        "options":[
                   "Yes",
                   "No",
                   ],
        "good": "Awesome! For some people it is important to sleep in a comfy bed.",
        "bad": "Maybe time for an upgrade?",
        "sol": [0],
        "suggestion": "Indulge yourself in a quality mattress, pillows, and bedcovers! It's worth it, I promise."
        },
        ];