import {useEffect, useState} from 'react';
import {SelectOptions} from '../../../components/selectOptions/SelectOptions';
import {Logo, MagicBulbIcon, SelectIcon} from '../../../assets';
import {OutlineDetail} from '../../../components/Outline/OutlineDetail';
import {motion} from "framer-motion";
import {createSlide, generateOutlines} from '../api/getSlides.tsx';
import {DragDropContext, Draggable} from 'react-beautiful-dnd';
import {StrictModeDroppable} from "./StrictModeDroppable.tsx";
import './outline.css';
import {useNavigate} from 'react-router-dom';
import {toast} from "react-toastify";
import {TypeOptions} from "react-toastify/dist/types";


export const Outline = () => {
    const [font, setFont] = useState('Montserrat');
    const [studyTime, setStudyTime] = useState('8 hours');
    const [studyMode, setStudyMode] = useState('fast');
    const [slideDimension, setSlideDimension] = useState('16:9');
    const [outlines, setOutlines] = useState([]);
    const [prompt, setPrompt] = useState('');

    const pageVariants = {
        initial: {opacity: 0},
        in: {opacity: 1},
        out: {opacity: 0},
    };

    const pageTransition = {
        type: 'tween',
        ease: 'anticipate',
        duration: 0.5,
    };

    const navigate = useNavigate();

    const handleCancel = () => {
        navigate('/');
    };

    const selectFont = (font: string) => setFont(font);
    const selectStudyTime = (time: string) => setStudyTime(time);
    const selectStudyMode = (mode: string) => setStudyMode(mode);
    const selectSlideDimension = (dimension: string) => setSlideDimension(dimension);

    const handleGenerateOutlines = async () => {
        if (prompt === '') {
            toast.error('Please type a prompt to generate outlines');
            const promptInput = document.querySelector('.promptInput');
            promptInput.classList.add('squiggle');
            setTimeout(() => promptInput.classList.remove('squiggle'), 500);
            return;
        }
        toast.info('Generating outlines...');
        const result = await generateOutlines(prompt);
        if (!result) {
            toast.error('Failed to generate outlines. Please try again.', { autoClose: 5000 });
            return;
        }
        result.forEach((outline) => {
            outline.id = Math.random().toString(36).substr(2, 9);
        });
        toast.success('Outlines generated successfully');
        setOutlines(result || []);
    };

    const handleConfirm = async () => {
        if (outlines.length === 0) {
            toast.error('No outlines generated. Please try again.');
            const promptInput = document.querySelector('.promptInput');
            promptInput.classList.add('squiggle');
            setTimeout(() => promptInput.classList.remove('squiggle'), 500);
        } else {
            const data = outlines.map(outline => ({
                title: outline.title,
                description: outline.description,
            }));
            const toastId = toast.info('Creating slides...', { autoClose: false });

            try {
                const response = await createSlide(data);
               toast.dismiss(toastId);
               toast.success('Slides created successfully');
                const id = response.id;
                navigate(`/slides/detail/${id}`);
            } catch (error) {
                console.error('Error:', error);
                toast.dismiss(toastId);
                toast.error('Failed to create slides. Please try again.', { autoClose: 5000 });
            }
        }
    }

    const handleOnDragEnd = (result) => {
        if (!result.destination) return;
        const items = Array.from(outlines);
        const [reorderedItem] = items.splice(result.source.index, 1);
        items.splice(result.destination.index, 0, reorderedItem);
        setOutlines(items);
    };

    useEffect(() => {
    }, [font, studyTime, studyMode, slideDimension]);

    return (
        <motion.div initial="initial" animate="in" exit="out" variants={pageVariants} transition={pageTransition}>
            <div className='h-screen relative'>
                <div
                    className='bg-[#01031A] flex justify-evenly h-[10vh] items-center px-10 border-0 border-b-[#c396fe3f] border-b-[1px]'>
                    <div className={'w-11/12'}>
                        <img src={Logo} alt='logo'/>
                    </div>
                    <div className={'w-full title-slide'}>Slide Generation</div>
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
                                        className={`rounded-full p-2 px-5 border-[2px] ${studyMode == 'fast' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'}`}
                                        onClick={() => selectStudyMode('fast')}> Fast
                                    </button>
                                    <button
                                        className={`rounded-full p-2 px-5 border-[2px] ${studyMode == 'quality' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'}`}
                                        onClick={() => selectStudyMode('quality')}> Quality
                                    </button>
                                </div>
                            </div>
                            <div className='study-mode mt-4'>
                                <div className='mb-2'>Slide Dimensions</div>
                                <div className='grid grid-cols-1 xl:grid-cols-3 gap-1'>
                                    <button
                                        className={`flex xl:flex-col items-center rounded-full p-2 py-6 border-[2px] ${slideDimension == '16:9' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'}`}
                                        onClick={() => selectSlideDimension('16:9')}>
                                        <div
                                            className='aspect-video border hidden 2xl:flex border-[#81818166] rounded-lg bg-[#6366F133]'>&nbsp;</div>
                                        <div className='mt-3'>16:9</div>
                                    </button>
                                    <button
                                        className={`flex xl:flex-col items-center rounded-full p-2 py-6 border-[2px] ${slideDimension == '3:2' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'}`}
                                        onClick={() => selectSlideDimension('3:2')}>
                                        <div
                                            className='aspect-[3/2] border hidden 2xl:flex  border-[#81818166] rounded-lg bg-[#6366F133]'>&nbsp;</div>
                                        <div className='mt-3'>3:2</div>
                                    </button>
                                    <button
                                        className={`flex xl:flex-col items-center rounded-full p-2 py-6 border-[2px] ${slideDimension == '1:1' ? 'border-[2px] border-[#6366F1]' : 'border-[#c396fe3f]'}`}
                                        onClick={() => selectSlideDimension('1:1')}>
                                        <div
                                            className='aspect-square max-h-[34px] border hidden 2xl:flex  border-[#81818166] rounded-lg bg-[#6366F133]'>&nbsp;</div>
                                        <div className='mt-3'>1:1</div>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <motion.div
                        className='col-span-4 w-[85%] p-2 relative'
                        style={{marginLeft: '10px', marginRight: '10px', marginTop: '20px'}}
                        initial={{opacity: 0, y: 250}}
                        animate={{opacity: 1, y: 0}}
                        transition={{duration: 1}}>
                        <div className='w-full flex justify-center'>
                            <div className='w-full bg-[#2D1BAF33] p-3 px-5 flex justify-between'
                                 style={{marginLeft: '10px', borderRadius: '20px'}}>
                                <input type="text" placeholder='Type a prompt ...'
                                       className='bg-transparent w-[85%] promptInput'
                                       value={prompt} onChange={(e) => setPrompt(e.target.value)}/>
                                <div className='w-[15%] flex justify-around'>
                                    <div
                                        className='h-full aspect-square flex justify-center items-center bg-[#5A2D841A] border border-[#81818133] hover:border-[#8181819d] cursor-pointer rounded-lg'>
                                        <img src={MagicBulbIcon} alt='magic bulb'/>
                                    </div>
                                    <button className='generate-button p-3 px-5 transition-all hover:scale-105'
                                            onClick={handleGenerateOutlines}>Generate
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className='h-[60%]'>
                            <div style={{marginLeft: '10px', marginTop: '10px'}}>Outline</div>
                            <motion.div className='overflow-y-scroll h-[100%]'>
                                <DragDropContext onDragEnd={handleOnDragEnd}>
                                    <StrictModeDroppable droppableId="outlines">
                                        {(provided) => (
                                            <div {...provided.droppableProps} ref={provided.innerRef} >
                                                {outlines.length === 0 ? (
                                                    <div className='text-center text-gray-500'>Type a prompt and click
                                                        Generate to get outlines</div>
                                                ) : (
                                                    outlines.map((outline, index) => (
                                                        <Draggable key={outline.id} draggableId={outline.id}
                                                                   index={index}>
                                                            {(provided) => (
                                                                <div
                                                                    ref={provided.innerRef} {...provided.draggableProps} {...provided.dragHandleProps}>
                                                                    <OutlineDetail index={index} title={outline.title}
                                                                                   outline_id={outline.id}
                                                                                   des={outline.description}/>
                                                                </div>
                                                            )}
                                                        </Draggable>
                                                    ))
                                                )}
                                                {provided.placeholder}
                                            </div>
                                        )}
                                    </StrictModeDroppable>
                                </DragDropContext>
                            </motion.div>
                            <button
                                className='w-full border border-[#C396FE80] rounded-full mt-4 p-2 bg-[#5A2D841A]'>Add
                                section
                            </button>
                            <div className='w-full flex justify-between mt-4'>
                                <button
                                    onClick={() => handleCancel()}
                                    className='cancel-button'>Cancel
                                </button>
                                <button className='confirm-button' onClick={() => handleConfirm()}>Confirm
                                </button>
                            </div>
                        </div>

                    </motion.div>
                </div>
            </div>
        </motion.div>
    )
        ;
};