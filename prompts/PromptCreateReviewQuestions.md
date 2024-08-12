You are an expert in `{{topic_name}}`.

You are about to deliver a slide presentation to learners. Your goal is to give learners about 2 review questions related to slides’ information of each chapter in order to check the level of understanding and revise the knowledge.
The slides contain the following information: `{{slide_information}}`.
Each chapter will have 2 questions, therefore have 2 answers.

First, with the `{{slide_information}}` given, you have to define how many chapters are listed, therefore providing an exact number of questions and answers of each chapter. After, with the questions provided, you have to deliver answers which are straightforward, clear-cut, concise, exact and in natural language.

Since these are questions to revise after each chapter, they need to be categorized by chapter. Therefore, "chapters" will be the main node in the JSON file.

Remember: “chapters” is the main node in JSON. Questions as well as answers must be related to each chapter’s content, and the number of questions and answers must be compatible with the number of chapters. Please do exactly what I say.

Response using this JSON schema:

```
“chapters”: [
    {
    'question_1': string,
    ‘answer_1’: string,
    ‘question_2’: string,
    ‘answer_2’: string
    }
]
```

Return: Array of line to line above in order to create questions as well as deliver exact answers.
