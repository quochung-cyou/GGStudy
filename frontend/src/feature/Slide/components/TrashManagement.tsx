import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { getSlides } from '../api/getSlides';
import { mockListSlide, mockSlideGet } from '../../../config/mockdata';
import { PreSlide } from '../../../components/slides/PreSlide';
import { SideBar } from '../../../components/sideBar/SideBar';
import './style.css'
export const TrashManagement = () => {
    const navigate = useNavigate();
    const [listSlide, setListSlide] = useState(mockListSlide)
    const data = mockSlideGet;
    const handle = async () => {
        console.log(data);
    }
    const handleClick = (slide_id: string) => {
        // chuyển route vào slide theo id ở chỗ này
        navigate(`/slides/detail/${slide_id}`);
    }
    return (
        <div className='flex h-[100vh]'>
            <div className='lg:w-[15%] min-w-[280px] '>
            <SideBar chooseTab={(tab: string) => 'trash'} />
            </div>
            <div className='lg:w-[85%] bg-[#01031A] px-10'>
                <div className=''>
                    <div className=''>
                        {/* <div>Thông tin chung về các slide, sắp xếp, lọc,</div> */}
                        <div className='w-full mt-10 relative'>
                            <div className='text-4xl'>Trash</div>
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
