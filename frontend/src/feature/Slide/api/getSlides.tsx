import { axios } from "../../../lib/axios";
import storage from "../../../utils/storage";

interface IGetSlides {
    size?: number,
    page?: number,
    sortBy?: string
}

export const getSlides = async (credentials: IGetSlides): Promise<any> => {
  try {
    const response = await axios.get("/projects");
    return response;
  } catch (error:any) {
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
  } catch (error:any) {
    return {
      data: null,
      message: error.message,
    };
  }
}