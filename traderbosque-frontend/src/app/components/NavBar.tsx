import {
    Navbar,
    NavbarBrand,
} from "flowbite-react";

export function NavBar() {
    return (
        <Navbar fluid rounded className="bg-[#1D5245] text-[#CB8A59]">
            <NavbarBrand href="../home">
                <img src='/traderbosque.png' className="mr-3 h-6 sm:h-9" alt="Acciones ElBosque" />
                <span className="self-center whitespace-nowrap text-xl font-semibold">
                    TraderBosque
                </span>
            </NavbarBrand>
        </Navbar>
    );
}