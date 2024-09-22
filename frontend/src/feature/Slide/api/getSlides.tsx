import {axios} from "../../../lib/axios";

interface IGetSlides {
    size?: number,
    page?: number,
    sortBy?: string
}

export const getSlides = async (credentials: IGetSlides): Promise<any> => {
    try {
        const response = await axios.get("/projects");
        return response;
    } catch (error: any) {
        return {
            data: null,
            message: error.message,
        };
    }
}

export const getSlideById = async (id: string): Promise<any> => {
    try {
        const response = await axios.get(`/projects/${id}`);
        return response;
    } catch (error: any) {
        return {
            data: null,
            message: error.message,
        };
    }
}

export const generateOutlines = async (topicName: string): Promise<any> => {
    try {
        const response = await axios.post(
            `/projects/outlines?topicName=${encodeURIComponent(topicName)}`
        );
        return response.data;
    } catch (error: any) {
        return {
            data: null,
            message: error.message,
        };
    }
};


export const getAnswer = async (newHistory: any[], currentMessage: string): Promise<any> => {
    try {
        const response = await axios.post('/projects/answers', {
            history: newHistory.map(item => item.message),
            question: currentMessage
        });

        return response.message;
    } catch (error: any) {
        return {
            message: error.message,
        };
    }
};

export const createSlide = async (data: any): Promise<any> => {
    try {
        const response = await axios.post('/projects', data);
        return response.data;
    } catch (error: any) {
        return {
            data: null,
            message: error.message,
        };
    }
};