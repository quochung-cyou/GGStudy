import {useEffect, useState} from 'react'
import {SelectOptions} from '../../../components/selectOptions/SelectOptions';
import {Logo, MagicBulbIcon, SelectIcon} from '../../../assets';
import {OutlineDetail} from '../../../components/Outline/OutlineDetail';
import {mockOutline} from '../../../config/mockdata';
import {motion} from "framer-motion";

export const Outline = () => {

    const [font, setFont] = useState('Montserrat')
    const [studyTime, setStudyTime] = useState('8 hours')
    const [studyMode, setStudyMode] = useState('fast')
    const [slideDimension, setSlideDimension] = useState('16:9')

    const pageVariants = {
        initial: {
            opacity: 0,
        },
        in: {
            opacity: 1,
        },
        out: {
            opacity: 0,
        },
    };

    const pageTransition = {
        type: 'tween',
        ease: 'anticipate',
        duration: 0.5,
    };

    const selectFont = (font: string) => {
        setFont(font)
        console.log(font);

    }
    const selectStudyTime = (time: string) => {
        setStudyTime(time)
    }
    const selectStudyMode = (mode: string) => {
        setStudyMode(mode)
    }
    const selectSlideDimension = (dimension: string) => {
        setSlideDimension(dimension)
    }
    useEffect(() => {
    }, [font, studyTime, studyMode, slideDimension])
    return (
        <motion.div
            initial="initial"
            animate="in"
            exit="out"
            variants={pageVariants}
            transition={pageTransition}
        >
            <div className='h-screen relative'>
                {/* ĐÂY LÀ THANH MENU BÊN TRÊN */}
                <div
                    className='bg-[#01031A] flex justify-between h-[10vh] items-center px-10 border-0 border-b-[#c396fe3f] border-b-[1px]'>
                    <div>
                        <img src={Logo} alt='logo'/>
                    </div>
                    <div>Slide Generation</div>
                    <button
                        className='bg-transparent rounded-lg border-[1px] border-white h-[80%] p-1 px-3 text-white'>SLIDE
                        SHOW
                    </button>
                </div>
                <div className='h-[90vh] w-full flex'>
                    <div
                        className='col-span-1 bg-[#6366F11A] w-[15%] border-0 border-r-[#c396fe3f] border-r-[1px] px-5 text-[20px]'>
                        <div className='border border-[#FFB4FF80] p-2 py-4 mt-4 rounded-2xl'>
                            <SelectOptions title='Font' icon={SelectIcon}
                                           defaultValue={['Times New Roman', 'Montserrat'].indexOf(font) + 1}
                                           choices={['Times New Roman', 'Montserrat']} des={'Choose font'}
                                           handleClick={selectFont}/>
                            <SelectOptions title='Study Time' icon={SelectIcon}
                                           defaultValue={['8 hours', '12 hours'].indexOf(studyTime) + 1}
                                           choices={['8 hours', '12 hours']} des={'Choose study time'}
                                           handleClick={selectStudyTime}/>
                            <div className='study-mode mt-4'>
                                <div className='mb-2'>Study Mode</div>
                                <div className='grid grid-cols-1 xl:grid-cols-2 gap-3'>
                                    <button
                                        className={`rounded-full p-2 px-5 border-[2px] ${studyMode == 'fast' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'} `}
                                        onClick={() => selectStudyMode('fast')}> Fast
                                    </button>
                                    <button
                                        className={`rounded-full p-2 px-5 border-[2px] ${studyMode == 'quality' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'} `}
                                        onClick={() => selectStudyMode('quality')}> Quality
                                    </button>
                                </div>
                            </div>
                            <div className='study-mode mt-4'>
                                <div className='mb-2'>Slide Dimensions</div>
                                <div className='grid grid-cols-1 xl:grid-cols-3 gap-1'>
                                    <button
                                        className={`flex xl:flex-col items-center rounded-full p-2 py-6 border-[2px] ${slideDimension == '16:9' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'} `}
                                        onClick={() => selectSlideDimension('16:9')}>
                                        <div
                                            className='aspect-video border hidden 2xl:flex border-[#81818166] rounded-lg bg-[#6366F133]'>&nbsp;</div>
                                        <div className='mt-3'>16:9</div>
                                    </button>
                                    <button
                                        className={`flex xl:flex-col items-center rounded-full p-2 py-6 border-[2px] ${slideDimension == '3:2' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'} `}
                                        onClick={() => selectSlideDimension('3:2')}>
                                        <div
                                            className='aspect-[3/2] border hidden 2xl:flex  border-[#81818166] rounded-lg bg-[#6366F133]'>&nbsp;</div>
                                        <div className='mt-3'>3:2</div>
                                    </button>
                                    <button
                                        className={`flex xl:flex-col items-center rounded-full p-2 py-6 border-[2px] ${slideDimension == '1:1' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'} `}
                                        onClick={() => selectSlideDimension('1:1')}>
                                        <div
                                            className='aspect-square max-h-[34px] border hidden 2xl:flex  border-[#81818166] rounded-lg bg-[#6366F133]'>&nbsp;</div>
                                        <div className='mt-3'>1:1</div>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='col-span-4 w-[85%] p-2 relative'>
                        <div className='w-full flex justify-center'>
                            <div className='w-full bg-[#2D1BAF33] p-3 px-5 flex justify-between rounded-xl'>
                                <input type="text" placeholder='Type a prompt ...' className='bg-transparent w-[85%]'/>
                                <div className='w-[15%] flex justify-around'>
                                    <div
                                        className='h-full aspect-square flex justify-center items-center bg-[#5A2D841A] border border-[#81818133] hover:border-[#8181819d] cursor-pointer rounded-lg'>
                                        <img src={MagicBulbIcon} alt='magic bulb'/>
                                    </div>
                                    <button className='generate-button p-3 px-5 hover:bg-red-700'
                                            onClick={() => console.log('abc')}>Generate
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className='h-[60%]'>
                            <div>Outline</div>
                            <div className='overflow-y-scroll h-[100%]'>
                                {mockOutline.map((outline, index) => (
                                    <OutlineDetail key={index} index={index} title={outline.title}
                                                   outline_id={outline.id} des={outline.des}/>
                                ))}
                            </div>
                            <button
                                className='w-full border border-[#C396FE80] rounded-full mt-4 p-2 bg-[#5A2D841A]'>Add
                                section
                            </button>
                        </div>
                        <div>

                        </div>
                    </div>
                </div>
            </div>
        </motion.div>
    )
}
