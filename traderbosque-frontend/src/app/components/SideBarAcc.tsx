
"use client";

import { Sidebar, SidebarItem, SidebarItemGroup, SidebarItems } from "flowbite-react";
import { BiBuoy } from "react-icons/bi";
import { HiArrowSmRight, HiChartPie, HiInbox, HiShoppingBag, HiTable, HiUser, HiViewBoards } from "react-icons/hi";
import { MdOutlineWorkspacePremium } from "react-icons/md";

/*
Sacado de FlowBite disponible en: https://flowbite-react.com/docs/components/sidebar
 */
export function SideBarAcc() {
    return (
        <Sidebar aria-label="Sidebar with content separator example">
            <SidebarItems>
                <SidebarItemGroup>
                    <SidebarItem href="../home" icon={HiChartPie}>
                        Dashboard
                    </SidebarItem>
                    <SidebarItem href="#" icon={HiViewBoards}>
                        Kanban
                    </SidebarItem>
                    <SidebarItem href="#" icon={HiInbox}>
                        Inbox
                    </SidebarItem>
                    <SidebarItem href="../account/info" icon={HiUser}>
                        Personal
                    </SidebarItem>
                    <SidebarItem href="../account/products" icon={MdOutlineWorkspacePremium}>
                        Premium
                    </SidebarItem>
                    <SidebarItem href="#" icon={HiArrowSmRight}>
                        Sign In
                    </SidebarItem>
                    <SidebarItem href="#" icon={HiTable}>
                        Sign Up
                    </SidebarItem>
                </SidebarItemGroup>
                <SidebarItemGroup>
                    <SidebarItem href="#" icon={HiChartPie}>
                        Upgrade to Pro
                    </SidebarItem>
                    <SidebarItem href="#" icon={HiViewBoards}>
                        Documentation
                    </SidebarItem>
                    <SidebarItem href="#" icon={BiBuoy}>
                        Help
                    </SidebarItem>
                </SidebarItemGroup>
            </SidebarItems>
        </Sidebar>
    );
}
