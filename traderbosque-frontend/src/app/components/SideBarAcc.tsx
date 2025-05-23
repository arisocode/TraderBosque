"use client"; // Necesario para Next.js y Flowbite-React

import { Sidebar, SidebarItem, SidebarItemGroup, SidebarItems } from "flowbite-react";
import { BiBuoy } from "react-icons/bi";
import { HiArrowSmRight, HiChartPie, HiInbox, HiTable, HiUser, HiViewBoards } from "react-icons/hi";
import { MdOutlineWorkspacePremium } from "react-icons/md";


export function SideBarAcc() {
    return (
        <Sidebar aria-label="Sidebar with content separator example" className="bg-[#28a745] text-[#CB8A59]">
            <SidebarItems>
                <SidebarItemGroup>
                    <SidebarItem href="/home" icon={HiViewBoards} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Inicio
                    </SidebarItem>
                    <SidebarItem href="/portfolio" icon={HiChartPie} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Portafolio
                    </SidebarItem>
                    <SidebarItem href="#" icon={HiInbox} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Inbox
                    </SidebarItem>
                    <SidebarItem href="/account/info" icon={HiUser} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Personal
                    </SidebarItem>
                    <SidebarItem href="/account/products" icon={MdOutlineWorkspacePremium} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Premium
                    </SidebarItem>
                    <SidebarItem href="/market" icon={HiTable} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Mercado
                    </SidebarItem>
                </SidebarItemGroup>
                <SidebarItemGroup>
                    <SidebarItem href="#" icon={BiBuoy} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Configuración
                    </SidebarItem>
                    <SidebarItem href=".." icon={HiArrowSmRight} className="hover:bg-[#CB8A59] hover:text-white rounded-md p-2">
                        Cerrar Sesión
                    </SidebarItem>
                </SidebarItemGroup>
            </SidebarItems>
        </Sidebar>
    );
}