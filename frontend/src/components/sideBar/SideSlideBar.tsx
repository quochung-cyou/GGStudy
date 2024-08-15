import {useEffect, useRef, useState} from 'react'
import { ISlide } from '../../inteface/dataInterface'
import './style.css'
interface ISideSlideBar extends React.HTMLAttributes<HTMLDivElement> {
    slides: ISlide[]
    handleClicked: (id: string) => void
    chooseIndex: number
}

export const SideSlideBar = ({ slides, chooseIndex, handleClicked, ...rest }: ISideSlideBar) => {
    const slideRefs = useRef<Array<HTMLDivElement | null>>([]);

    // useEffect(() => {
    //     // Cuộn mục được chọn vào tầm nhìn
    //     if (slideRefs.current[chooseIndex]) {
    //         slideRefs.current[chooseIndex].scrollIntoView({
    //             behavior: 'smooth',  // Cuộn mượt mà
    //             block: 'nearest',    
    //             inline: 'center'    // Không thay đổi cuộn ngang
    //         });
    //     }
    // }, [chooseIndex]);
    return (
        <div>
            {slides.map((slide, index) => (
                <div key={index}
                ref={(el) => (slideRefs.current[index] = el)}
                onClick={() => handleClicked(slides[index].id)} className={`button cursor-pointer my-2 py-1 flex items-center rounded-sm text-sm text-left hover:bg-[#6366F11A] gap-2 border-0 border-l-[5px] border-l-transparent truncate line-clamp-1 ${chooseIndex == index ? 'active' : ''}`}>
                    {slide.headingTitle}
                </div>
            ))}
        </div>
    )
}
