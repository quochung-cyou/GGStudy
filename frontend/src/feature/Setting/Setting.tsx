import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { SideBar } from '../../components/sideBar/SideBar';
import './style.css'
export const Setting = () => {
    const navigate = useNavigate();
    return (
        <div className='flex h-[100vh]'>
            <div className='lg:w-[15%] min-w-[280px] '>
                <SideBar chooseTab={(tab: string) => 'settings'} />
            </div>
            <div className='lg:w-[85%] bg-[#01031A] px-10 py-16'>
                <div className=''>
                    <div className=''>
                        <div className='w-full mt-10 relative'>
                            <div className='text-4xl'>Settings</div>
                        </div>
                    </div>
                    <div className=''>
                        <div className='grid lg:grid-cols-4 gap-5 mt-10'>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
