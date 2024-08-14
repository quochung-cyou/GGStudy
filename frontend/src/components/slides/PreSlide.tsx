import React from 'react'
import { loveIcon, loveIconBlank, moreIcon } from '../../assets'

interface PreSlideProps extends React.HTMLAttributes<HTMLDivElement> {
    slide_id: string
    title: string
    createBy: string
    modifyBy: string
    createTime: number
    modifyTime: number
    lastSeenSlide: number
    handleClick: (slide_id: string) => void
}

export const PreSlide = ({ slide_id, title, content, createBy, modifyBy, modifyTime, createTime, lastSeenSlide, handleClick, ...rest }: PreSlideProps) => {

    return (
        <div className='w-full relative' {...rest}>
            <div className='cursor-pointer relative bg-[#01031A] rounded-lg overflow-hidden border border-[#C396FE80] hover:ring-1'>
                <div className='absolute flex top-4 right-4 z-10'>
                    <div className='bg-[#00000040] hover:bg-[#000000a4] rounded-full aspect-square p-1' onClick={() => console.log('love')}>
                        <img src={loveIconBlank} alt='love' />
                    </div>
                    <div className='bg-[#00000040] hover:bg-[#000000a4] rounded-full aspect-square p-1 ml-2'>
                        <img src={moreIcon} alt='more' />
                    </div>
                </div>
                <div className='p-3'  onClick={() => handleClick(slide_id)}>
                    <div className='relative bg-white w-full aspect-video rounded-lg'>
                        <div>PREVIEW</div>
                    </div>
                </div>
                <div className='flex pl-[5%] flex-col w-full min-h-[40px] mb-3' onClick={() => handleClick(slide_id)}>
                    <div className='w-[85%] line-clamp-1'>
                        {title}
                    </div>
                    <div className='w-[85%] text-sm'>
                        {lastSeenSlide}
                    </div>
                </div>
            </div>
        </div>
    )
}
