import React from 'react'

interface PreSlideProps extends React.HTMLAttributes<HTMLDivElement> {
    slide_id: number
    slide_name: string
    // content là 1 object json chứa nội dung của slide
    content?: any
    handleClick: (slide_id: number) => void
}

export const PreSlide = ({ slide_id, slide_name, content, handleClick, ...rest }: PreSlideProps) => {

    return (
        <div className='w-full relative' {...rest}>
            <div className='cursor-pointer rounded-lg overflow-hidden hover:ring-1' onClick={() => handleClick(slide_id)}>
                <div className='bg-white w-full aspect-video '>
                    PREVIEW
                </div>
                <div className='flex pl-[5%] bg-gray-200 w-full min-h-[40px]'>
                    <div className='w-[85%] overflow-hidden line-clamp-1'>
                        {slide_name}
                    </div>
                </div>
            </div>
        </div>
    )
}
