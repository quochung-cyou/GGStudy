import React, {useEffect, useRef} from 'react'
import {ISlide} from '../../inteface/dataInterface'
import GoogleFontLoader from "react-google-font";

interface PreSlideMiniProps extends React.HTMLAttributes<HTMLDivElement> {
    slides: ISlide[]
    handleClick: (id: string) => void
    chooseIndex: number
}

export const PreSlideMini = ({slides, handleClick, chooseIndex, ...rest}: PreSlideMiniProps) => {
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
    const miniDiff = (211 / 1109);
    const percentDiff = 1109.633 / 1920 * miniDiff;
    const percentDiffFont = 101/134*0.9 * miniDiff;

    <GoogleFontLoader
        fonts={[
            {
                font: 'Poppins',
                weights: [400, 900]
            }
        ]}
        subsets={['cyrillic-ext', 'greek']}
    />

    return (
        <div className='w-full flex'>
            {slides.map((item, index) => (
                <div key={item.id}
                     ref={(el) => tabRefs.current[index] = el}
                     className={`m-4 mx-2 h-full aspect-mini ${chooseIndex == index ? `ring ring-red-400 -translate-x-[calc(${300 * index}px)]` : ''}`}>
                    <div className='h-full relative cursor-pointer' {...rest} onClick={() => handleClick(item.id)}>
                        <div
                            className='bg-white text-black relative h-full aspect-video rounded-2xl hover:ring-1 overflow-hidden'>
                            {item.elements.map((item) => {
                                let fontWeightStyle = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "fontWeight") : undefined;
                                let textTransform = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "textTransform") : undefined;
                                let fontSize = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "fontSize") : undefined;
                                let letterSpacing = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "letterSpacing") : undefined;
                                let lineHeight = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "lineHeight") : undefined;
                                if (item.elementType == 'TEXT') {
                                    return (
                                        <div key={item.id} className={`text-start absolute`} style={{
                                            top: `calc(${item.posY * percentDiff}px)`,
                                            left: `calc(${item.posX * percentDiff}px)`,
                                            maxWidth: `calc(${item.sizeX * percentDiff}px)`,
                                            maxHeight: `calc(${item.sizeY * percentDiff}px)`,
                                            zIndex: item.layer,
                                            letterSpacing: letterSpacing ? `calc(${letterSpacing.propertyValue/1000}em)` : undefined
                                        }}>
                                            <p style={{
                                                fontSize: fontSize ? `${fontSize.propertyValue * percentDiffFont}px` : `calc(${20 * percentDiff}px)`,
                                                fontWeight: fontWeightStyle ? fontWeightStyle.propertyValue : undefined,
                                                fontFamily: 'Poppins, sans-serif',
                                                textTransform: textTransform ? textTransform.propertyValue : undefined,
                                                lineHeight: lineHeight ? `${lineHeight.propertyValue*fontSize.propertyValue*percentDiffFont}px` : undefined,
                                                wordBreak: 'break-word'
                                            }}>{item.content}</p>
                                        </div>
                                    )
                                } else if (item.elementType == 'HEADING') {
                                    return (
                                        <div key={item.id} className={`text-start absolute`} style={{
                                            top: `calc(${item.posY * percentDiff}px)`,
                                            left: `calc(${item.posX * percentDiff}px)`,
                                            maxWidth: `calc(${item.sizeX * percentDiff}px)`,
                                            maxHeight: `calc(${item.sizeY * percentDiff}px)`,
                                            zIndex: item.layer,
                                            letterSpacing: letterSpacing ? `calc(${letterSpacing.propertyValue/1000}em)` : undefined
                                        }}>
                                            <p style={{
                                                fontSize: fontSize ? `${fontSize.propertyValue * percentDiffFont}px` : `calc(${60 * percentDiff}px)`,
                                                fontWeight: fontWeightStyle ? fontWeightStyle.propertyValue : undefined,
                                                fontFamily: 'Poppins, sans-serif',
                                                textTransform: textTransform ? textTransform.propertyValue : undefined,
                                                lineHeight: lineHeight ? `${lineHeight.propertyValue*fontSize.propertyValue*percentDiffFont}px` : undefined,
                                                wordBreak: 'break-word'
                                            }}>{item.content}</p>
                                        </div>
                                    )
                                } else if (item.elementType == 'IMAGE') {
                                    return (
                                        <div key={item.id} className={`absolute`} style={{
                                            top: `calc(${item.posY * percentDiff}px)`,
                                            left: `calc(${item.posX * percentDiff}px)`,
                                            width: `calc(${item.sizeX * percentDiff}px)`,
                                            height: `calc(${item.sizeY * percentDiff}px)`,
                                            zIndex: item.layer
                                        }}>
                                            <img className='relative' src={item.imageUrl} alt={item.headingTitle}
                                                 style={{objectFit: 'cover', width: '100%', height: '100%'}}/>
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