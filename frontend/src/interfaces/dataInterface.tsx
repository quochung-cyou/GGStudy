export interface IMockSlideDetail {
    id: string
    title: string
    createBy: string
    modifyBy: string
    createTime: number
    modifyTime: number
    lastSeenSlide: number
    slides: {
        id: string
        headingTitle: string
        topicName: string
        elements: {
            id: string
            slideId: string
            templateId: string
            elementType: string
            headingTitle: string
            topicName: string
            content: string
            layer: number
            appearOrder: number
            sizeX: number
            sizeY: number
            posX: number
            posY: number
            imageUrl: string
            duration: number
        }[]
        usernotes: any[]
    }[]
}

