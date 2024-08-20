import React, {useEffect, useState} from 'react'
import {PreSlideMini} from '../../../components/slides/PreSlideMini';
import {mockSlideDetailGet} from '../../../config/mockdata';
import {useParams} from 'react-router-dom';
import {Logo} from '../../../assets';
import {getSlideById} from '../api/getSlides';
import {SideSlideBar} from '../../../components/sideBar/SideSlideBar';
import GoogleFontLoader from 'react-google-font'

export const SlideDetail = () => {
    const {id = ''} = useParams()
    const [data, setData] = useState(mockSlideDetailGet);
    const [currentSlide, setCurrentSlide] = useState(data.slides[0])
    const [currentSlideIndex, setCurrentSlideIndex] = useState(0)
    const changeSlide = (slide_id: string) => {
        const nextSlide = data.slides.find((item) => item.id == slide_id)
        if (nextSlide) {
            setCurrentSlide(nextSlide)
            setCurrentSlideIndex(data.slides.findIndex((item) => item.id == slide_id))
        }
        console.log(currentSlideIndex);
    }
    // khi change slide thì sẽ cập nhật hiển thị <SideSlideBar>

    const percentDiff = 1109.633 / 1920;
    const percentDiffFont = 101/134*0.9;

    <GoogleFontLoader
        fonts={[
            {
                font: 'Poppins',
                weights: [400, 900]
            }
        ]}
        subsets={['cyrillic-ext', 'greek']}
    />

    useEffect(() => {
        const fetchSlide = async () => {
            const slideget = await getSlideById(id);
            setData(slideget.data);
            setCurrentSlide(slideget.data.slides[0]);
        }
        fetchSlide();
    }, [id])
    return (
        <div className='h-screen relative'>
            {/* ĐÂY LÀ THANH MENU BÊN TRÊN */}
            <div className='bg-[#01031A] flex justify-between h-[10vh] items-center px-10'>
                <img src={Logo} alt='logo'/>
                <div>{currentSlide.headingTitle}</div>
                <button
                    className='bg-transparent rounded-full border-[1px] border-white h-[80%] p-1 px-3 text-white'>SLIDE
                    SHOW
                </button>
            </div>

            {/* ĐÂY LÀ CONTENT BÊN DƯỚI */}
            <div className='h-[90vh]  w-full flex relative'>
                <div className='col-span-1 bg-[#6366F11A] w-[20%] p-3 px-5 overflow-y-scroll'>
                    <SideSlideBar slides={data.slides} handleClicked={changeSlide} chooseIndex={currentSlideIndex}/>
                </div>
                <div className='col-span-4 w-[60%] px-2 relative'>
                    {/* Đoạn này là slide chính */}
                    <div className='w-full flex justify-center items-center relative'>
                        {/* <div className={`h-[calc(${slideHeight})] w-auto aspect-video bg-white`}> */}
                        <div className={`text-black  aspect-video bg-white relative overflow-hidden`}
                             style={{width: '1109px', height: '624px'}}>
                            {currentSlide.elements.map((item) => {
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

                    {/* Đây là preview các mini slide  */}
                    <div className='h-[20%] w-full overflow-x-scroll '>
                        <div className='flex h-[80%] '>
                            {/* Khi click vào 1 slide mini thì sẽ hiển thị slide đó lên slide chính thay thế cho currentSlide */}
                            <PreSlideMini slides={data.slides} handleClick={changeSlide}
                                          chooseIndex={currentSlideIndex}/>
                        </div>
                    </div>
                    <div className='h-[20%] w-full relative'>
                        <div className='text-3xl'>Note</div>
                        <div className='w-full  h-full'>
                            <textarea className='w-full text-white bg-transparent' disabled
                                      defaultValue={data.slides[currentSlideIndex].usernotes}/>
                        </div>
                    </div>
                </div>
                <div className='col-span-1 w-[20%] p-5'>
                    <div
                        className='p-5 bg-[#5A2D841A] border border-[#C396FE80] rounded-2xl w-full h-full flex justify-between flex-col'>
                        <div className='mb-3 flex justify-between h-[5%]'>
                            <div>GGStudy Chatbot</div>
                            <div></div>
                        </div>
                        <div className=' h-[85%]'>
                            <div className="divider"></div>
                            CONTENT
                        </div>
                        <div className=' h-[5%]'>
                            <div className="divider"></div>
                            <input type="text" className='w-full rounded-full'/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
