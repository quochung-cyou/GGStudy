import { useEffect, useState } from 'react'
import { PreSlideMini } from '../../../components/slides/PreSlideMini';
import { mockDataSlide, mockDataSlideList, mockListSlide, mockSlideDetailGet } from '../../../config/mockdata';
import { useParams } from 'react-router-dom';
import { Logo } from '../../../assets';
import { getSlideById } from '../api/getSlides';
import { SideSlideBar } from '../../../components/sideBar/SideSlideBar';
export const SlideDetail = () => {
    const { id = '' } = useParams()
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



    useEffect(() => {
        const fetchSlide = async () => {
            const slideget = await getSlideById(id);
            setData(slideget);
            setCurrentSlide(slideget.slides[0]);
        }
        fetchSlide();
    }, [id])
    return (
        <div className='h-screen'>
            {/* ĐÂY LÀ THANH MENU BÊN TRÊN */}
            <div className='bg-[#01031A] flex justify-between h-[10vh] items-center px-10'>
                <img src={Logo} alt='logo' />
                <div>{currentSlide.headingTitle}</div>
                <button className='bg-transparent rounded-full border-[1px] border-white h-[80%] p-1 px-3 text-white'>SLIDE SHOW</button>
            </div>

            {/* ĐÂY LÀ CONTENT BÊN DƯỚI */}
            <div className='h-[90vh]  w-full flex'>
                <div className='col-span-1 bg-[#6366F11A] w-[20%] p-3 px-5 overflow-y-scroll'>
                    <SideSlideBar slides={data.slides} handleClicked={changeSlide} chooseIndex={currentSlideIndex} />
                </div>
                <div className='col-span-4 w-[60%] px-2'>
                    {/* Đoạn này là slide chính */}
                    <div className='w-full flex justify-center items-center '>
                        {/* <div className={`h-[calc(${slideHeight})] w-auto aspect-video bg-white`}> */}
                        <div className={`text-black w-[80%] aspect-video bg-white relative overflow-hidden`}>
                            {currentSlide.elements.map((item) => {
                                if (item.elementType == 'TEXT') {
                                    return (
                                        <div key={item.id} className={`text-center z-[${item.layer}] relative`} style={{ top: `calc(${item.posY}px)`, left: `calc(${item.posX}px)`, width: `calc(${item.sizeX}px)`, height: `calc(${item.sizeY}px)` }}>
                                            <div className='text-3xl relative' style={{ top: `calc(${item.posY}px)`, left: `calc(${item.posX}px)`, width: `calc(${item.sizeX}px)`, height: `calc(${item.sizeY}px)` }}>{item.headingTitle}</div>
                                            <div className='text-2xl relative' style={{ top: `calc(${item.posY}px)`, left: `calc(${item.posX}px)`, width: `calc(${item.sizeX}px)`, height: `calc(${item.sizeY}px)` }}>{item.content}</div>
                                        </div>
                                    )
                                }
                                else if (item.elementType == 'IMAGE') {
                                    return (
                                        <div key={item.id} className={`z-[${item.layer}] relative`} style={{ top: `calc(${item.posY}px)`, left: `calc(${item.posX}px)`, width: `calc(${item.sizeX}px)`, height: `calc(${item.sizeY}px)` }}>
                                            <img className='relative' src={item.imageUrl} alt={item.headingTitle} style={{ top: `calc(${item.posY}px)`, left: `calc(${item.posX}px)`, width: `calc(${item.sizeX}px)`, height: `calc(${item.sizeY}px)` }} />
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
                            <PreSlideMini slides={data.slides} handleClick={changeSlide} chooseIndex={currentSlideIndex} />
                        </div>
                    </div>
                    <div className='h-[20%] w-full relative'>
                        <div className='text-3xl'>Note</div>
                        <div className='w-full  h-full'>
                            <textarea className='w-full text-white bg-transparent' disabled defaultValue={data.slides[currentSlideIndex].usernotes} />
                        </div>
                    </div>
                </div>
                <div className='col-span-1 w-[20%] p-5'>
                    <div className='p-5 bg-[#5A2D841A] border border-[#C396FE80] rounded-2xl w-full h-full flex justify-between flex-col'>
                        <div className='mb-3 flex justify-between h-[5%]'>
                            <div>GGStudy Chatbot</div>
                            <div></div>
                        </div>
                        <div className=' h-[85%]'>
                            <div className="divider"></div>CONTENT</div>
                        <div className=' h-[5%]'>
                            <div className="divider"></div>
                            <input type="text" className='w-full rounded-full' />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
