export interface ISlide {
    id: string;
    headingTitle: string;
    topicName: string;
    elements: {
        id: string;
        slideId: string;
        templateId: string;
        elementType: string;
        headingTitle: string;
        topicName: string;
        content: string;
        layer: number;
        appearOrder: number;
        posX: number;
        posY: number;
        sizeX: number;
        sizeY: number;
        imageUrl: string;
        duration: number;
    }[];
    usernotes: never[];
}

