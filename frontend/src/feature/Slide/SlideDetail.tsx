import { useState } from 'react'
// Component hiển thị chi tiết của 1 bộ slide. 
// Nó sẽ là màn hiển thị gồm: Thanh menu gồm: tên, nút trình chiếu.
// Giao diện bên dưới gồm: 1 div to nhất để nhìn slide hiện tại, 1 bottom bar để hiển thị (preview) các slide khác trong bộ theo dạng mini.
// Có 1 nút thêm mới slide ở cuối phần preview slide mini.
// Có 1 nút fixed để ấn vào sẽ hiển thị boxchat để chat với AI.
import { PreSlideMini } from '../../components/slides/PreSlideMini';
import { mockDataSlide, mockDataSlideList, mockListSlide } from '../../config/mockdata';
import { useParams } from 'react-router-dom';

export const SlideDetail = () => {
    const { id } = useParams()

    // const [slide, setSlide] = useState(mockDataSlide)
    const slide = mockListSlide.find((item) => item.id == Number(id)) || mockDataSlide
    const [currentSlide, setCurrentSlide] = useState(mockDataSlideList[0])
    const changeSlide = (slide_id: number) => {
        const newSlide = slide.data.find((item) => item.id === slide_id) as { id: number; name: string; content: {} }
        setCurrentSlide(newSlide)
        console.log('change slide', newSlide);

    }
    return (
        <div className='bg-[#eeeeee] h-screen'>
            {/* ĐÂY LÀ THANH MENU BÊN TRÊN */}
            <div className='bg-blue-500 flex justify-between h-[5vh] items-center px-10'>
                <div>Logo</div>
                <div>{slide.title}</div>
                <button className='bg-transparent rounded-lg border-[1px] border-white h-[80%] p-1 px-3 text-white'>SLIDE SHOW</button>
            </div>

            {/* ĐÂY LÀ CONTENT BÊN DƯỚI */}
            <div className='h-[95vh] bg-[#eeeeee] w-full'>

                {/* Đoạn này là slide chính */}
                <div className='w-full h-[80%] flex justify-center items-center bg-red-100'>
                    {/* <div className={`h-[calc(${slideHeight})] w-auto aspect-video bg-white`}> */}
                    <div className={`h-[80%] w-auto aspect-video bg-white`}>
                        {currentSlide.name}
                    </div>
                </div>

                {/* Đây là preview các mini slide  */}
                <div className='h-[20%] w-full overflow-x-scroll'>
                    <div className='flex h-[80%]'>
                        {/* Khi click vào 1 slide mini thì sẽ hiển thị slide đó lên slide chính thay thế cho currentSlide */}
                        {slide.data.map((item) => (
                            <div key={item.id} className='aspect-mini'>
                                <PreSlideMini slide_id={item.id} slide_name={item.name} content={item.content} handleClick={() => changeSlide(item.id)} />
                            </div>
                        ))}
                        {/* Preview các slide khác */}
                        {/* danh sách các slide mini */}
                        <div className='m-4 mx-2 h-full bg-blue-100 relative'>
                            <div className='bg-white h-full aspect-video rounded-2xl flex justify-center items-center'>
                                ADD SLIDE
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className='fixed'>Nút chat với AI</div>
        </div>
    )
}
