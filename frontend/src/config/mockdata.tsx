export const mockDataSlideList = [
    {
        id: 1,
        name: 'Slide 1 1 23  45 678 909 876543 2345 67 90987654123 45678909876543234567 89098765412345678909 876543234567890987654',
        content: {},
    },
    {
        id: 2,
        name: 'Slide 2',
        content: {},
    },
    {
        id: 3,
        name: 'Slide 3',
        content: {},
    },
    {
        id: 4,
        name: 'Slide 4',
        content: {},
    },
    {
        id: 5,
        name: 'Slide 5',
        content: {},
    },
    {
        id: 6,
        name: 'Slide 6',
        content: {},
    },
    {
        id: 7,
        name: 'Slide 7',
        content: {},
    },
    {
        id: 8,
        name: 'Slide 8',
        content: {},
    },
    {
        id: 9,
        name: 'Slide 9',
        content: {},
    },
    {
        id: 10,
        name: 'Slide 10',
        content: {},
    },
]
export const mockDataSlide = {
    id: 0,
    title: 'Slide Generation',
    data: mockDataSlideList,
}

export const mockListSlide = [
    // có khoảng 7 slide
    {
        id: 1,
        title: 'Slide Generation',
        lastEdit: 'Description 1',
        data: mockDataSlideList,
    },
    {
        id: 2,
        title: 'Slide Generation',
        lastEdit: 'Description 2',
        data: mockDataSlideList,
    },
    {
        id: 3,
        title: 'Slide Generation',
        lastEdit: 'Description 3',
        data: mockDataSlideList,
    },
    {
        id: 4,
        title: 'Slide Generation',
        lastEdit: 'Description 4',
        data: mockDataSlideList,
    },
    {
        id: 5,
        title: 'TITLE SLIDE 5',
        lastEdit: 'Description 5',
        data: mockDataSlideList,
    },
    {
        id: 6,
        title: 'TITLE SLIDE 6',
        lastEdit: 'Description 6',
        data: mockDataSlideList,
    },
    {
        id: 7,
        title: 'TITLE SLIDE 7',
        lastEdit: 'Description 7',
        data: mockDataSlideList,
    },
    {
        id: 8,
        title: 'TITLE SLIDE 8',
        lastEdit: 'Description 8',
        data: mockDataSlideList,
    },
    {
        id: 9,
        title: 'TITLE SLIDE 9',
        lastEdit: 'Description 9',
        data: mockDataSlideList,
    },
    {
        id: 10,
        title: 'TITLE SLIDE 10',
        lastEdit: 'Description 10',
        data: mockDataSlideList,
    },

]

// ---
export const mockSlideGet = {
    "data": {
        "content": [
            {
                "id": "67d616be-912e-1876-8191-2e0929240000",
                "title": "",
                "createBy": "admin",
                "modifyBy": "admin",
                "createTime": 1723054238000,
                "modifyTime": 1723054238000,
                "lastSeenSlide": 0
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 20,
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 1,
        "size": 20,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 1,
        "empty": false
    },
    "message": "Project list."
}

export const mockSlideDetailGet = {
    "id": "67d616be-912e-1876-8191-2e0929240000",
    "title": "",
    "createBy": "admin",
    "modifyBy": "admin",
    "createTime": 1723054238000,
    "modifyTime": 1723054238000,
    "lastSeenSlide": 0,
    "slides": [
        {
            "id": "67d616be-912e-1876-8191-2e0929450001",
            "headingTitle": "",
            "topicName": "Learning Python",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929460002",
                    "slideId": "67d616be-912e-1876-8191-2e0929450001",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "",
                    "topicName": "Learning Python",
                    "content": "Introduction to Python",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e0929460003",
            "headingTitle": "What is Python?",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929460004",
                    "slideId": "67d616be-912e-1876-8191-2e0929460003",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "What is Python?",
                    "topicName": "",
                    "content": "Python is a high-level, interpreted, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A computer screen displaying Python code with colorful syntax highlighting.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e0929460005",
                    "slideId": "67d616be-912e-1876-8191-2e0929460003",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "What is Python?",
                    "topicName": "",
                    "content": "Python is a high-level, interpreted, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e0929470006",
            "headingTitle": "Python in Action",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929470007",
                    "slideId": "67d616be-912e-1876-8191-2e0929470006",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Web Development",
                    "topicName": "",
                    "content": "Python is widely used for web development with frameworks like Django and Flask.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A screenshot of a web browser displaying a simple website built with a Python web framework.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e0929470008",
                    "slideId": "67d616be-912e-1876-8191-2e0929470006",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Python in Action",
                    "topicName": "",
                    "content": "",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e0929470009",
                    "slideId": "67d616be-912e-1876-8191-2e0929470006",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Data Science",
                    "topicName": "",
                    "content": "Python is a popular choice for data science and machine learning due to its powerful libraries like Pandas and Scikit-learn.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A screenshot of a data visualization tool with a Python script generating a chart.",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e092947000a",
            "headingTitle": "",
            "topicName": "Learning Python",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e092947000b",
                    "slideId": "67d616be-912e-1876-8191-2e092947000a",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "",
                    "topicName": "Learning Python",
                    "content": "Basic Syntax",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e092947000c",
            "headingTitle": "Variables and Data Types",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e092948000d",
                    "slideId": "67d616be-912e-1876-8191-2e092947000c",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Variables and Data Types",
                    "topicName": "",
                    "content": "Variables store data in Python.  Different data types are used to represent various kinds of information.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet showing variable assignment with different data types (integer, string, boolean) and their values.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e092948000e",
                    "slideId": "67d616be-912e-1876-8191-2e092947000c",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Variables and Data Types",
                    "topicName": "",
                    "content": "Variables store data in Python.  Different data types are used to represent various kinds of information.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e092948000f",
            "headingTitle": "Operators and Expressions",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929480010",
                    "slideId": "67d616be-912e-1876-8191-2e092948000f",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Arithmetic Operators",
                    "topicName": "",
                    "content": "Used for basic mathematical operations.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e0929480011",
                    "slideId": "67d616be-912e-1876-8191-2e092948000f",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Operators and Expressions",
                    "topicName": "",
                    "content": "",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e0929480012",
                    "slideId": "67d616be-912e-1876-8191-2e092948000f",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Comparison Operators",
                    "topicName": "",
                    "content": "Used to compare values and return True or False.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet demonstrating comparison operators (==, !=, \u003C, \u003E, \u003C=, \u003E=)",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e0929480013",
            "headingTitle": "",
            "topicName": "Learning Python",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929480014",
                    "slideId": "67d616be-912e-1876-8191-2e0929480013",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "",
                    "topicName": "Learning Python",
                    "content": "Control Flow",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e0929490015",
            "headingTitle": "Conditional Statements (if, elif, else)",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929490016",
                    "slideId": "67d616be-912e-1876-8191-2e0929490015",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Conditional Statements (if, elif, else)",
                    "topicName": "",
                    "content": "Conditional statements execute different blocks of code based on a condition being True or False.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet showing an if-elif-else statement with indentation highlighting different code blocks.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e0929490017",
                    "slideId": "67d616be-912e-1876-8191-2e0929490015",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Conditional Statements (if, elif, else)",
                    "topicName": "",
                    "content": "Conditional statements execute different blocks of code based on a condition being True or False.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e0929490018",
            "headingTitle": "Loops (for, while)",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e0929490019",
                    "slideId": "67d616be-912e-1876-8191-2e0929490018",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "For Loop",
                    "topicName": "",
                    "content": "Used for iterating over a sequence of items.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet showing a for loop iterating through a list of items.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e092949001a",
                    "slideId": "67d616be-912e-1876-8191-2e0929490018",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Loops (for, while)",
                    "topicName": "",
                    "content": "",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e092949001b",
                    "slideId": "67d616be-912e-1876-8191-2e0929490018",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "While Loop",
                    "topicName": "",
                    "content": "Used for repeating a block of code until a condition becomes False.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet showing a while loop executing as long as a condition is True.",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e092949001c",
            "headingTitle": "",
            "topicName": "Learning Python",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e092949001d",
                    "slideId": "67d616be-912e-1876-8191-2e092949001c",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "",
                    "topicName": "Learning Python",
                    "content": "Functions",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e09294a001e",
            "headingTitle": "Defining and Calling Functions",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e09294a001f",
                    "slideId": "67d616be-912e-1876-8191-2e09294a001e",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Defining and Calling Functions",
                    "topicName": "",
                    "content": "Functions are reusable blocks of code that perform specific tasks.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet showing a function definition with a name, parameters, and a return statement.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e09294a0020",
                    "slideId": "67d616be-912e-1876-8191-2e09294a001e",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Defining and Calling Functions",
                    "topicName": "",
                    "content": "Functions are reusable blocks of code that perform specific tasks.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                }
            ],
            "usernotes": []
        },
        {
            "id": "67d616be-912e-1876-8191-2e09294a0021",
            "headingTitle": "Function Arguments",
            "topicName": "",
            "elements": [
                {
                    "id": "67d616be-912e-1876-8191-2e09294a0022",
                    "slideId": "67d616be-912e-1876-8191-2e09294a0021",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Positional Arguments",
                    "topicName": "",
                    "content": "Arguments passed in the same order as the function's parameters.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 10,
                    "posY": 10,
                    "imageUrl": "A code snippet showing a function being called with positional arguments.",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e09294a0023",
                    "slideId": "67d616be-912e-1876-8191-2e09294a0021",
                    "templateId": "",
                    "elementType": "TEXT",
                    "headingTitle": "Function Arguments",
                    "topicName": "",
                    "content": "",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 5,
                    "sizeY": 5,
                    "posX": 5,
                    "posY": 5,
                    "imageUrl": "example.com/text",
                    "duration": 3
                },
                {
                    "id": "67d616be-912e-1876-8191-2e09294a0024",
                    "slideId": "67d616be-912e-1876-8191-2e09294a0021",
                    "templateId": "",
                    "elementType": "IMAGE",
                    "headingTitle": "Keyword Arguments",
                    "topicName": "",
                    "content": "Arguments passed using parameter names for clarity.",
                    "layer": 1,
                    "appearOrder": 1,
                    "sizeX": 10,
                    "sizeY": 10,
                    "posX": 60,
                    "posY": 60,
                    "imageUrl": "A code snippet showing a function being called with keyword arguments.",
                    "duration": 3
                }
            ],
            "usernotes": []
        }
    ]
}

export const mockOutline = [
    // có khoảng 6 cái outline
    {
        id: '1',
        title: 'Introduction to Python',
        des: 'Introduction to Python',
    },
    {
        id: '2',
        title: 'What is Python?',
        des: 'What is Python?',
    },
    {
        id: '3',
        title: 'Python in Action',
        des: 'Python in Action',
    },
    {
        id: '4',
        title: 'Basic Syntax',
        des: 'Basic Syntax',
    },
    {
        id: '5',
        title: 'Variables and Data Types',
        des: 'Variables and Data Types',
    },
    {
        id: '6',
        title: 'Operators and Expressions',
        des: 'Operators and Expressions',
    },
    {
        id: '7',
        title: 'Control Flow',
        des: 'Control Flow',
    },
    {
        id: '8',
        title: 'Conditional Statements (if, elif, else)',
        des: 'Conditional Statements (if, elif, else)',
    },
    {
        id: '9',
        title: 'Loops (for, while)',
        des: 'Loops (for, while)',
    },
    {
        id: '10',
        title: 'Functions',
        des: 'Functions',
    },
    {
        id: '11',
        title: 'Defining and Calling Functions',
        des: 'Defining and Calling Functions',
    },
    {
        id: '12',
        title: 'Function Arguments',
        des: 'Function Arguments',
    },
]