
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
                    <SidebarItem href="../home" icon={HiViewBoards}>
                        Home
                    </SidebarItem>
                    <SidebarItem href="../portfolio" icon={HiChartPie}>
                        Portfolio
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
                    <SidebarItem href="../market" icon={HiTable}>
                        Market
                    </SidebarItem>
                    <SidebarItem href="../login" icon={HiArrowSmRight}>
                        Log out
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
