import './App.css'
import AppBar from "./AppBar.tsx";
import {useEffect} from "react";
import gsap from 'gsap';
import {useGSAP} from '@gsap/react';


import dotanimation from './animations/dot.js'
import universe from './assets/universe.png'
import video from './assets/video.mp4'
import speedtesticon from './assets/speed-test.svg'
import examicon from './assets/exam.svg'
import usericon from './assets/users 02.svg'
import charticon from './assets/bar chart.svg'
import studenticon from './assets/student.svg'
import shufflingicon from './assets/shuffle.svg'


function App() {

    useEffect(() => {
        gsap.registerPlugin(useGSAP);
        dotanimation();
    }, [])

    return (
        <div className="main-container">
            <AppBar/>
            <div className="hero-section">
                <div className="hero-text">
                    <h1 className="hero-title">Revolutionize Learning with AI-Driven Slides</h1>
                    <p className="hero-description">Learn Faster, Smarter, and More Efficiently with Personalized AI
                        Courses</p>
                </div>
                <div className="hero-button-section">
                    <button className="hero-button-generate">
                        <svg className="icon" viewBox="0 0 24 26">
                            <path
                                d="M5.16515 2.62145L5.8075 0.999247C5.83876 0.919722 5.9154 0.866699 6.00112 0.866699C6.08683 0.866699 6.16347 0.919722 6.19473 0.999247L6.83708 2.62145L8.44145 3.27094C8.5201 3.30254 8.57254 3.38003 8.57254 3.4667C8.57254 3.55337 8.5201 3.63085 8.44145 3.66246L6.83708 4.31195L6.19473 5.93415C6.16347 6.0147 6.08683 6.0667 6.00112 6.0667C5.9154 6.0667 5.83876 6.0147 5.8075 5.93415L5.16515 4.31195L3.56078 3.66246C3.48112 3.63085 3.42969 3.55337 3.42969 3.4667C3.42969 3.38003 3.48112 3.30254 3.56078 3.27094L5.16515 2.62145Z"
                            />
                            <path
                                d="M11.2362 9.43967C11.5502 9.30067 11.8015 9.05025 11.9405 8.73617L13.5494 5.11725C13.7169 4.74204 14.0887 4.5 14.5 4.5C14.9112 4.5 15.2839 4.74204 15.4506 5.11725L17.0603 8.73617C17.1985 9.05025 17.4497 9.3015 17.7638 9.43967L21.3827 11.0494C21.7579 11.2161 22 11.5887 22 12C22 12.4112 21.7579 12.7831 21.3827 12.9506L17.7638 14.5595C17.4497 14.6985 17.1993 14.9497 17.0603 15.2638L15.4506 18.8827C15.2839 19.2579 14.9112 19.5 14.5 19.5C14.0887 19.5 13.7169 19.2579 13.5494 18.8827L11.9405 15.2638C11.8015 14.9497 11.5502 14.6985 11.2362 14.5595L7.61725 12.9506C7.24204 12.7831 7 12.4112 7 12C7 11.5887 7.24204 11.2161 7.61725 11.0494L11.2362 9.43967Z"
                            />
                            <path
                                d="M4.60728 19.392L5.67703 16.6875C5.72997 16.5541 5.85854 16.4666 6.00056 16.4666C6.14258 16.4666 6.27031 16.5541 6.32325 16.6875L7.39299 19.392L10.0678 20.4736C10.1997 20.5271 10.2863 20.6563 10.2863 20.7999C10.2863 20.9435 10.1997 21.0735 10.0678 21.1271L7.39299 22.2087L6.32325 24.9123C6.27031 25.0457 6.14258 25.1332 6.00056 25.1332C5.85854 25.1332 5.72997 25.0457 5.67703 24.9123L4.60728 22.2087L1.93333 21.1271C1.8014 21.0735 1.71484 20.9435 1.71484 20.7999C1.71484 20.6563 1.8014 20.5271 1.93333 20.4736L4.60728 19.392Z"
                            />
                        </svg>
                        <a href="#" className="button-text">Generate</a>
                    </button>
                    <a href="#" className="hero-text-learn">Learn More</a>
                </div>
            </div>
            <div className="hero-image">
                <img src={universe} alt="Universe" className="universe-image"/>
                <div className="video-container">
                    <video autoPlay muted loop className="video">
                        <source src={video} type="video/mp4"/>
                    </video>
                </div>
            </div>
            <div className="feature-section" style={{marginTop: "30rem"}}>
                <div className="feature-card">
                    <img src={speedtesticon} alt="Speed Test Icon" className="speed-test-icon"/>
                    <h2 className="feature-title">Need to master a new skill quickly</h2>
                    <p className="feature-description">Whether you’re learning to code, improving your design skills, or
                        picking up a new language, GGStudy helps you get there faster with tailored, interactive lessons
                        that keep you engaged and on track.</p>
                </div>
                <div className="feature-card">
                    <img src={examicon} alt="Exam Icon" className="exam-icon"/>
                    <h2 className="feature-title">Efficient Exam Preparation</h2>
                    <p className="feature-description">Skip the endless textbooks and focus on what matters. Our AI
                        breaks down complex topics into bite-sized, easily digestible slides, helping you retain
                        information and ace your exams.</p>
                </div>
                <div className="feature-card">
                    <img src={usericon} alt="User Icon" className="user-icon"/>
                    <h2 className="feature-title">Looking to upskill your team</h2>
                    <p className="feature-description">Equip your employees with the latest knowledge and skills through
                        personalized training programs. Our AI-driven platform ensures that each team member learns at
                        their own pace, maximizing productivity.</p>
                </div>
            </div>
            <div className="feature-section">
                <div className="feature-card">
                    <img src={charticon} alt="Chart Icon" className="chart-icon"/>
                    <h2 className="feature-title">Continuous Professional Development</h2>
                    <p className="feature-description">Stay ahead of the curve with ongoing learning opportunities.
                        GGStudy helps professionals in any field continuously improve by providing the latest industry
                        insights and best practices. .</p>
                </div>
                <div className="feature-card">
                    <img src={studenticon} alt="Student Icon" className="student-icon"/>
                    <h2 className="feature-title">Personal Growth and Hobby Exploration</h2>
                    <p className="feature-description">Explore new passions and hobbies with our AI-powered courses.
                        Whether it’s photography, cooking, or writing, our platform makes learning fun and accessible,
                        helping you turn interests into expertise.</p>
                </div>
                <div className="feature-card">
                    <img src={shufflingicon} alt="Shuffling Icon" className="shuffling-icon"/>
                    <h2 className="feature-title">Adaptive Learning for Students</h2>
                    <p className="feature-description">Students of all ages can benefit from personalized tutoring that
                        adapts to their learning style. GGStudy identifies areas for improvement and tailors content to
                        ensure better understanding and higher grades..</p>
                </div>
            </div>
        </div>


    )
}

export default App
