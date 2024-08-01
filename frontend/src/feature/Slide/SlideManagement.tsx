import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

// Đây là component quản lý các bộ slide.
// Có 1 thanh sidebar bên trái để hiển thị thông tin tài khoản, các lựa chọn abc, xyz (Hiện chưa phát triển).
// Phần còn lại sẽ gồm 2 phần: 1 phần bên trên sẽ hiện thông tin chung về các slide, sắp xếp, lọc và 1 nút "Tạo mới". Phần 2 là phần bên dưới, sẽ hiển thị các bộ slide mà người dùng đã tạo (theo grid).

import { mockListSlide } from '../../config/mockdata';
import { PreSlide } from '../../components/slides/PreSlide';
export const SlideManagement = () => {
    const navigate = useNavigate();
    const [listSlide, setListSlide] = useState(mockListSlide)
    const handleClick = (slide_id: number) => {
        // chuyển route vào slide theo id ở chỗ này
        console.log(slide_id);
        navigate(`detail/${slide_id}`);
    }
    return (
        <div className='flex h-[100vh]'>

            <div className='lg:w-[15%] bg-white'>
                <div>LOGO</div>
                <div>PROFILE</div>
                <div>MENU</div>
            </div>
            <div className='lg:w-[85%] bg-red-100 px-10 py-16'>
                <div className=''>
                    <div className=''>
                        <button className='bg-transparent border-[1px] border-white rounded-lg p-3 px-5 hover:bg-red-700'>GENERATE NEW</button>
                        <div>Thông tin chung về các slide, sắp xếp, lọc,</div>
                    </div>
                    <div className='grid lg:grid-cols-6 gap-5 mt-10'>
                        {listSlide.map((slide) => (
                            <PreSlide slide_id={slide.id} slide_name={slide.title} handleClick={() => handleClick(slide.id)} />
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )
}
