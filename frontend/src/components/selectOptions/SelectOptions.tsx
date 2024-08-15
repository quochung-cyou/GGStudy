import React from 'react'
import { DownIcon, SelectIcon } from '../../assets'
interface SelectOptionsProps extends React.HTMLAttributes<HTMLDivElement> {
  icon?: string
  title: string
  des?: string
  defaultValue: number
  handleClick?: (font: string) => void;
  choices: string[]
}

export const SelectOptions = ({ icon, title, des, defaultValue, choices, handleClick, ...rest }: SelectOptionsProps) => {
  const [isShow, setIsShow] = React.useState(false)
  const handleOptionClick = (item: string) => {
    if (handleClick) {
      handleClick(item);
    }
    setIsShow(false); // Ẩn các lựa chọn sau khi chọn
  };
  return (
    <div className='w-full relative cursor-pointer pt-4'>
      <div className='' onClick={() => setIsShow(!isShow)}>
        <div className='bg-[#1d0a2e] bg-[#5A2D841A] rounded-full p-3 px-5 flex gap-2 items-center border border-[C396FE80] overflow-hidden '>
          {icon &&
            <div className='w-[15%]'>
              <img src={icon} alt='' className='' />
            </div>
          }
          <div className={`${title ? 'w-[85%]' : 'w-full'} flex justify-between items-center`}>
            <div>
              {title &&
                <div className='text-[#868686]'>{title}</div>
              }
              <div className='line-clamp-1 text-sm'>{choices && choices[defaultValue - 1] || des}</div>
            </div>
            <div>
              <img src={DownIcon} alt='' />
            </div>
          </div>
        </div>
      </div>
      {isShow && (
        <div className='options absolute origin-top top-[100%] right-0 w-full z-10'>
          {choices && choices.map((item, index) => (
            <div
              key={index}
              className='text-white bg-[#1d0a2e] p-3 px-5 hover:bg-[#8943ca]'
              onClick={() => handleOptionClick(item)}
            >
              {item}
            </div>
          ))}
        </div>
      )}
    </div>
  )
}
