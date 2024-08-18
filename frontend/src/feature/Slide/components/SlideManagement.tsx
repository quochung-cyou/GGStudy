import  { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { getSlides } from '../api/getSlides';
import { mockSlideGet } from '../../../config/mockdata';
import { PreSlide } from '../../../components/slides/PreSlide';
import { SideBar } from '../../../components/sideBar/SideBar';
import { MagicBulbIcon } from '../../../assets';
import './style.css'
export const SlideManagement = () => {
    const navigate = useNavigate();
    const [data, setData] = useState(mockSlideGet);

    useEffect(() => {
        const fetchSlide = async () => {
            const slide = await getSlides({ size: 20, page: 0, sortBy: 'id' });
            setData(slide);
        }
        fetchSlide();
    }, [])
    const handleClick = (slide_id: string) => {
        navigate(`/slides/detail/${slide_id}`);
    }
    return (
        <div className='flex h-[100vh]'>
            <div className='lg:w-[15%] min-w-[280px] '>
                <SideBar chooseTab={(tab: string) => 'home'} />
            </div>
            <div className='lg:w-[85%] bg-[#01031A] px-10 py-16'>
                <div className=''>
                    <div className=''>
                        <div className='w-full bg-[#2D1BAF33] p-3 px-5 flex justify-between rounded-xl'>
                            <input type="text" placeholder='Type a prompt ...' className='bg-transparent w-[85%]' />
                            <div className='w-[15%] flex justify-around'>
                                <div className='h-full aspect-square flex justify-center items-center bg-[#5A2D841A] border border-[#81818133] hover:border-[#8181819d] cursor-pointer rounded-lg'>
                                    <img src={MagicBulbIcon} alt='magic bulb' />
                                </div>
                                <button className='generate-button p-3 px-5 hover:bg-red-700' onClick={() => console.log('abc')}>Generate</button>
                            </div>
                        </div>
                        {/* <div>Thông tin chung về các slide, sắp xếp, lọc,</div> */}
                        <div className='w-full mt-10 relative'>
                            <div className='text-4xl'>Home</div>
                        </div>
                    </div>
                    <div className=''>
                        <div className='grid lg:grid-cols-4 gap-5 mt-10'>
                            {data.data.content.map((slide, index) => (
                                <PreSlide key={index} slide_id={slide.id} title={slide.title || "TITLE"} createBy={slide.createBy} modifyBy={slide.modifyBy} createTime={slide.createTime} modifyTime={slide.modifyTime} lastSeenSlide={slide.lastSeenSlide} handleClick={() => handleClick(slide.id)} />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
/* showcase placebo */
