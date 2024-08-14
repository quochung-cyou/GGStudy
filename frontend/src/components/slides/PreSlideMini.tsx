import React, { useEffect, useRef } from 'react'
import { ISlide } from '../../inteface/dataInterface'

interface PreSlideMiniProps extends React.HTMLAttributes<HTMLDivElement> {
    slides: ISlide[]
    handleClick: (id: string) => void
    chooseIndex: number
}
export const PreSlideMini = ({ slides, handleClick, chooseIndex, ...rest }: PreSlideMiniProps) => {
    const tabRefs = useRef<(HTMLElement | null)[]>([]);

    useEffect(() => {
        if (tabRefs.current[chooseIndex]) {
            tabRefs.current[chooseIndex].scrollIntoView({
                behavior: 'smooth',  
                block: 'nearest',    
                inline: 'center'     
            });
        }
    }, [chooseIndex]);
    return (
        <div className='w-full flex'>
            {slides.map((item, index) => (
                <div key={item.id} 
                ref={(el) => tabRefs.current[index] = el} 
                className={`m-4 mx-2 h-full aspect-mini ${chooseIndex == index ? `ring ring-red-400 -translate-x-[calc(${300 * index}px)]` : ''}`}>
                    <div className='h-full relative cursor-pointer' {...rest} onClick={() => handleClick(item.id)}>
                        <div className='bg-white text-black relative h-full aspect-video rounded-2xl hover:ring-1 overflow-hidden'>
                            {item.elements.map((item) => {
                                if (item.elementType == 'TEXT') {
                                    return (
                                        <div key={item.id} className={`w-full text-center z-[${item.layer}] relative`} style={{ top: `calc(${item.posY / 10}px)`, left: `calc(${item.posX / 10}px)`, width: `calc(${item.sizeX / 10}px)`, height: `calc(${item.sizeY / 10}px)` }}>
                                            <div className='origin-center scale-[0.2] text-3xl relative w-full' style={{ top: `calc(${item.posY / 10}px)`, left: `calc(${item.posX / 10}px)`, width: `calc(${item.sizeX / 10}px)`, height: `calc(${item.sizeY / 10}px)` }}>{item.headingTitle}</div>
                                            <div className='origin-center scale-[0.2] text-2xl relative w-full' style={{ top: `calc(${item.posY / 10}px)`, left: `calc(${item.posX / 10}px)`, width: `calc(${item.sizeX / 10}px)`, height: `calc(${item.sizeY / 10}px)` }} >{item.content}</div>
                                        </div>
                                    )
                                }
                                else if (item.elementType == 'IMAGE') {
                                    return (
                                        <div key={item.id} className={`z-[${item.layer}] relative`} style={{ top: `calc(${item.posY / 10}px)`, left: `calc(${item.posX / 10}px)`, width: `calc(${item.sizeX / 10}px)`, height: `calc(${item.sizeY / 10}px)` }}>
                                            <img className='scale-[0.2] relative' src={item.imageUrl} alt={item.headingTitle} style={{ top: `calc(${item.posY / 10}px)`, left: `calc(${item.posX / 10}px)`, width: `calc(${item.sizeX / 10}px)`, height: `calc(${item.sizeY / 10}px)` }} />
                                        </div>
                                    )
                                }
                            })}
                        </div>
                    </div>
                    {/* <PreSlideMini slide_id={item.id} headingTitle={item.headingTitle || ""} topicName={item.topicName || ""} elements={item.elements} usernotes={item.usernotes} chooseIndex={ } handleClick={() => changeSlide(item.id)} /> */}
                </div>
            ))}

        </div>
    )
}