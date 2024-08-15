import React from 'react'
import { homeIcon, favoriteIcon, trashIcon, accountIcon, settingIcon, feedbackIcon, signOutIcon } from '../../assets'

import './style.css'
import { useNavigate } from 'react-router-dom'
interface SideBarProps extends React.HTMLAttributes<HTMLDivElement> {
    chooseTab?: (tab: string) => string
}


interface ButtonProps extends React.HTMLAttributes<HTMLDivElement> {
    icon: string
    title: string
    active?: boolean
    handleClick?: () => void
}

const Button = ({ icon, title, active, handleClick, ...props }: ButtonProps) => {
    return (
        <div className={`button cursor-pointer my-2 py-2 flex items-center rounded-sm text-left hover:bg-[#6366F11A] gap-2 border-0 border-l-[5px] border-l-transparent ${active ? 'active' : ''}`} onClick={handleClick} {...props}>
            <div>
                <img src={icon} alt={title} />
            </div>
            <div className='text-[14px] font-[600]'>
                {title}
            </div>
        </div>
    )
}

export const SideBar = ({chooseTab}: SideBarProps) => {
    const navigation = useNavigate()
    const getActiveState = (tab: string) => {
        // Placeholder logic to determine if a button is active
        // You should adjust this based on your actual requirements
        return chooseTab ? chooseTab(tab) === tab : false;
    }
    return (
        <div className=' fixed w-[15%] bg-[#6366F11A] min-w-[280px] px-5 pr-10 h-full border-r-[#C396FE80] border border-l-0 border-y-0 pt-5'>
            <div className='flex items-center my-3'>
                <div className='aspect-square p-5 bg-green-500 rounded-full h-[56px] flex justify-center items-center mr-3'>
                    <div className='text-3xl font-bold'>M</div>
                </div>
                <div>
                    <div>MH007</div>
                    <div className='text-[#818181] font-light text-[14px]'>hanhtm.mpc@gmail.com</div>
                </div>
            </div>
            <div className='w-full relative'>
                <div className='my-5'>MENU</div>
                <Button icon={homeIcon} title='Home' active={getActiveState('home')} handleClick={() => navigation('/slides/home')} />
                <Button icon={favoriteIcon} title='Favorites' active={getActiveState('favorites')} handleClick={() => navigation('/slides/favorites')} />
                <Button icon={trashIcon} title='Trash' active={getActiveState('trash')} handleClick={() => navigation('/slides/trash')} />
                <div className="divider">&nbsp;</div>
                <Button icon={accountIcon} title='Account Information' active={getActiveState('account')} handleClick={() => navigation('/account')} />
                <Button icon={settingIcon} title='Settings' active={getActiveState('settings')} handleClick={() => navigation('/settings')} />
                <Button icon={feedbackIcon} title='Feedback' active={getActiveState('feedback')} handleClick={() => navigation('/feedback')} />
                <div className="divider">&nbsp;</div>
                <Button icon={signOutIcon} title='Sign out' active={getActiveState('sign-out')} handleClick={() => navigation('/sign-out')} />
            </div>
        </div>
    )
}


