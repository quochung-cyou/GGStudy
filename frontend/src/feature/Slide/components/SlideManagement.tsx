import {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom';
import {getSlides} from '../api/getSlides';
import {mockSlideGet} from '../../../config/mockdata';
import {PreSlide} from '../../../components/slides/PreSlide';
import {SideBar} from '../../../components/sideBar/SideBar';
import {MagicBulbIcon} from '../../../assets';
import './style.css'

export const SlideManagement = () => {
    const navigate = useNavigate();
    const [data, setData] = useState(mockSlideGet);

    useEffect(() => {
        const fetchSlide = async () => {
            const slide = await getSlides({size: 20, page: 0, sortBy: 'id'});
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
                <SideBar chooseTab={(tab: string) => 'home'}/>
            </div>
            <div className='lg:w-[85%] bg-[#01031A] px-10 py-16'>
                <div className=''>
                    <div className=''>
                        {/* <div>Thông tin chung về các slide, sắp xếp, lọc,</div> */}
                        <div className='w-full mt-10 relative'>
                            <div className='text-4xl'>Home</div>
                        </div>
                    </div>
                    <div className=''>
                        <div className='grid lg:grid-cols-4 gap-5 mt-10'>
                            {data.data.content.map((slide, index) => (
                                <PreSlide key={index} slide_id={slide.id} title={slide.title || "TITLE"}
                                          createBy={slide.createBy} modifyBy={slide.modifyBy}
                                          createTime={slide.createTime} modifyTime={slide.modifyTime}
                                          lastSeenSlide={slide.lastSeenSlide}
                                          handleClick={() => handleClick(slide.id)}/>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
/* showcase placebo */
