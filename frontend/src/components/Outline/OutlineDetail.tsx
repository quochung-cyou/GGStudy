import React from 'react'
import { EditIcon } from '../../assets'
interface OutlineDetailProps extends React.HTMLAttributes<HTMLDivElement> {
    outline_id: string
    title: string
    des: string
    index: number
}

export const OutlineDetail = ({ index, outline_id, title, des, ...rest }: OutlineDetailProps) => {
    // const handleEdit = () => {
    //     // cho các input có thể chỉnh sửa
    //     const allInput = document.querySelectorAll('input')
    //     allInput.forEach((input) => {
    //         input.classList.add('border-b-2', 'border-[#1A112E]')
    //         if (input.disabled) {
    //             input.disabled = false
    //         } else {
    //             input.disabled = true
    //         }
    //     })
    // }
    // Chỉ handle edit đúng cái thôi, cái nào handle thì cái đó được chỉnh sửa
    const handleEdit = () => {
        // cho các input có thể chỉnh sửa
        const allInput = document.querySelectorAll('input')
        allInput.forEach((input) => {
            if (input.disabled) {
                input.classList.add('border-2', 'border-[#1A112E]')
                input.disabled = false
            } else {
                input.classList.remove('border-2', 'border-[#1A112E]')
                input.disabled = true
            }
        })
    }

    return (
        <div className='w-full flex items-center border-0 border-l-[4px] border-l-[#1A112E] my-3 px-5 p-1'>
            <div className='text-[48px] mr-3 font-[600]'>{index}</div>
            <div className='w-full flex justify-between'>
                <div className='flex flex-col'>
                    <input className='text-xl bg-transparent border-transparent border-[2px]' defaultValue={title} disabled />
                    <input className='text-sm text-[#818181] bg-transparent border-transparent border-[2px]' defaultValue={des} disabled />
                </div>
                {/* <div className='cursor-pointer p-2 rounded-2xl hover:bg-[#4d43ac5b] aspect-square h-[40px] flex justify-center items-center' onClick={handleEdit}>
                    <img src={EditIcon} alt='edit' />
                </div> */}
            </div>
        </div>
    )
}