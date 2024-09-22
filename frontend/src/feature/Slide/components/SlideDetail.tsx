import React, {useEffect, useState} from 'react'
import {PreSlideMini} from '../../../components/slides/PreSlideMini';
import {mockSlideDetailGet} from '../../../config/mockdata';
import {useNavigate, useParams} from 'react-router-dom';
import {Logo} from '../../../assets';
import {getAnswer, getSlideById} from '../api/getSlides';
import {SideSlideBar} from '../../../components/sideBar/SideSlideBar';
import GoogleFontLoader from 'react-google-font'
import './SlideDetail.css'
import ctmenu from '../../../animations/contextmenu';

export const SlideDetail = () => {
    const {id = ''} = useParams()
    const [data, setData] = useState(mockSlideDetailGet);
    const [currentSlide, setCurrentSlide] = useState(data.slides[0])
    const [currentSlideIndex, setCurrentSlideIndex] = useState(0)
    const navigate = useNavigate();

    const [history, setHistory] = useState([
        { sender: 'user', message: 'What is GGStudy?' },
        { sender: 'bot', message: 'GGStudy is a smart learning platform that uses artificial intelligence (AI), specifically Gemini, to create customized learning slides, facilitating efficient and accessible learning through a structured roadmap and continuous interaction.' }
    ]);
    const [currentMessage, setCurrentMessage] = useState('');

    const handleSendMessage = async () => {
        if (currentMessage.trim() === '') return;

        const newHistory = [...history, { sender: 'user', message: currentMessage }];
        setHistory(newHistory);
        setCurrentMessage('');

        const data = await getAnswer(newHistory, currentMessage);
        setHistory(prevHistory => [...prevHistory, { sender: 'bot', message: data }]);
    };

    useEffect(() => {
        const chatContainer = document.querySelector('.chatbot-messages');
        if (chatContainer) {
            chatContainer.scrollTop = chatContainer.scrollHeight;
        }
    }, [history]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCurrentMessage(e.target.value);
    };


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

    const handleLogoClick = () => {
        navigate('/slides/home');
    };

    useEffect(() => {
        const fetchSlide = async () => {
            const slideget = await getSlideById(id);
            setData(slideget.data);
            setCurrentSlide(slideget.data.slides[0]);
        }
        fetchSlide();
    }, [id])


    useEffect(() => {
        ctmenu();
        // Event listener for the custom event
        const handleUpdateTextContent = (event) => {
            setCurrentMessage(event.detail.textContent);
        };

        document.addEventListener('updateTextContent', handleUpdateTextContent);

        // Cleanup event listener on component unmount
        return () => {
            document.removeEventListener('updateTextContent', handleUpdateTextContent);
        };
    }, []);


    return (
        <div className='h-screen relative'>
            {/* ĐÂY LÀ THANH MENU BÊN TRÊN */}
            <div className='bg-[#01031A] flex justify-between h-[7vh] items-center px-10'>
                <img src={Logo} alt='logo' onClick={handleLogoClick}/>
                <div className={'title-slide'}>{currentSlide.headingTitle}</div>
                <button
                    className='bg-transparent rounded-full border-[1px] border-white h-[80%] p-1 px-3 text-white'>SLIDE
                    SHOW
                </button>
            </div>

            {/* ĐÂY LÀ CONTENT BÊN DƯỚI */}
            <div className='w-full flex relative downcontent'>
                <div className='col-span-1 bg-[#6366F11A] w-[20%] p-3 px-5 overflow-y-scroll downcontent'>
                    <SideSlideBar slides={data.slides} handleClicked={changeSlide} chooseIndex={currentSlideIndex}/>
                </div>
                <div className='col-span-4 w-[60%] px-2 relative'>
                    {/* Đoạn này là slide chính */}
                    <div className='w-full flex justify-center items-center relative'>
                        {/* <div className={`h-[calc(${slideHeight})] w-auto aspect-video bg-white`}> */}
                        <div className={`text-black  aspect-video bg-white relative overflow-hidden mainslide target target-light`}
                             style={{width: '1109px', height: '624px'}}>
                            {currentSlide.elements.map((item) => {
                                let fontWeightStyle = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "fontWeight") : undefined;
                                let textTransform = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "textTransform") : undefined;
                                let fontSize = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "fontSize") : undefined;
                                let letterSpacing = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "letterSpacing") : undefined;
                                let lineHeight = item.fieldStyles ? item.fieldStyles.find(style => style.propertyName === "lineHeight") : undefined;
                                if (item.elementType == 'TEXT') {
                                    return (
                                        <div
                                            type={item.elementType}
                                            key={item.id}
                                            className={`text-start absolute slide-element`} style={{
                                            top: `calc(${item.posY * percentDiff}px)`,
                                            left: `calc(${item.posX * percentDiff}px)`,
                                            maxWidth: `calc(${item.sizeX * percentDiff}px)`,
                                            maxHeight: `calc(${item.sizeY * percentDiff}px)`,
                                            zIndex: item.layer,
                                            letterSpacing: letterSpacing ? `calc(${letterSpacing.propertyValue / 1000}em)` : undefined
                                        }}>
                                            <p style={{
                                                fontSize: fontSize ? `${fontSize.propertyValue * percentDiffFont}px` : `calc(${20 * percentDiff}px)`,
                                                fontWeight: fontWeightStyle ? fontWeightStyle.propertyValue : undefined,
                                                fontFamily: 'Poppins, sans-serif',
                                                textTransform: textTransform ? textTransform.propertyValue : undefined,
                                                lineHeight: lineHeight ? `${lineHeight.propertyValue * fontSize.propertyValue * percentDiffFont}px` : undefined,
                                                wordBreak: 'break-word'
                                            }}>{item.content}</p>
                                        </div>
                                    )
                                } else if (item.elementType == 'HEADING') {
                                    return (
                                        <div
                                            type={item.elementType}
                                            key={item.id}
                                            className={`text-start absolute slide-element`} style={{
                                            top: `calc(${item.posY * percentDiff}px)`,
                                            left: `calc(${item.posX * percentDiff}px)`,
                                            maxWidth: `calc(${item.sizeX * percentDiff}px)`,
                                            maxHeight: `calc(${item.sizeY * percentDiff}px)`,
                                            zIndex: item.layer,
                                            letterSpacing: letterSpacing ? `calc(${letterSpacing.propertyValue / 1000}em)` : undefined
                                        }}>
                                            <p style={{
                                                fontSize: fontSize ? `${fontSize.propertyValue * percentDiffFont}px` : `calc(${60 * percentDiff}px)`,
                                                fontWeight: fontWeightStyle ? fontWeightStyle.propertyValue : undefined,
                                                fontFamily: 'Poppins, sans-serif',
                                                textTransform: textTransform ? textTransform.propertyValue : undefined,
                                                lineHeight: lineHeight ? `${lineHeight.propertyValue * fontSize.propertyValue * percentDiffFont}px` : undefined,
                                                wordBreak: 'break-word'
                                            }}>{item.content}</p>
                                        </div>
                                    )
                                } else if (item.elementType == 'IMAGE') {
                                    return (
                                        <div
                                            type={item.elementType}
                                            key={item.id}
                                            className={`absolute slide-element`} style={{
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
                    <div className='w-full'>
                        <div className='text-3xl'>Note</div>
                        <div className=''>
                            <textarea className='w-full text-white bg-transparent' disabled
                                      defaultValue={data.slides[currentSlideIndex].usernotes}/>
                        </div>
                    </div>
                </div>
                <div className='col-span-1 w-[30%] container-chat'>
                    <div className='chatbot-container'>
                        <div className='chatbot-title'>GGStudy Chatbot</div>
                        <div className='chatbot-messages'>
                            {history.map((item, index) => (
                                <div key={index} className={`message ${item.sender}`}>
                                    {item.message}
                                </div>
                            ))}
                        </div>
                        <input
                            type='text'
                            placeholder='Type your message here'
                            className='inputChatBot'
                            value={currentMessage}
                            onChange={handleInputChange}
                            onKeyDown={(e) => e.key === 'Enter' && handleSendMessage()}
                        />
                    </div>
                </div>
            </div>
        </div>
    )
}
