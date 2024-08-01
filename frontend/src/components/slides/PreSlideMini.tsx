import React from 'react'

interface PreSlideMiniProps extends React.HTMLAttributes<HTMLDivElement> {
    slide_id: number
    slide_name: string
    // content là 1 object json chứa nội dung của slide
    content?: any
    handleClick: (slide_id: number) => void
}

export const PreSlideMini = ({ slide_id, slide_name, content, handleClick, ...rest }: PreSlideMiniProps) => {
    
    return (
        <div className='m-4 mx-2 h-full relative cursor-pointer' onClick={() => handleClick(slide_id)}>
            <div className='bg-white h-full aspect-video rounded-2xl hover:ring-1'>
                PREVIEW
            </div>
            <div className='flex absolute bottom-[5%] pl-[5%] bg-green-200 w-full'>
                <div className='w-[15%]'>
                    {slide_id}
                </div>
                <div className='w-85%] overflow-hidden line-clamp-1'>
                    {slide_name}
                </div>
            </div>
        </div>
    )
}
