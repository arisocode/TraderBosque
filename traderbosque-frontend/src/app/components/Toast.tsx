import { showToast } from "nextjs-toast-notify";
import React, { useEffect, useRef } from 'react';

interface Prop {
    message: string;
}

export const SuccessToast: React.FC<Prop> = ({ message }) => {
    const shown = useRef(false);
    useEffect(() => {
        if (!shown.current) {
            shown.current = true;
            showToast.success(message, {
                duration: 5000,
                progress: true,
                position: "top-right",
                transition: "fadeIn",
                icon: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.75" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-check"><path d="M20 6 9 17l-5-5"/></svg>',
                sound: false,
            });
        }
    }, [message]);

    return null;
};

export const ErrorToast: React.FC<Prop> = ({ message }) => {
    const shown = useRef(false);
    useEffect(() => {
        if (!shown.current) {
            shown.current = true;
            showToast.error(message, {
                duration: 4000,
                progress: true,
                position: "top-right",
                transition: "fadeIn",
                icon: '',
                sound: false,
            });
        }
    }, [message]);


    return null;
};

export const WarningToast: React.FC<Prop> = ({ message }) => {
    const shown = useRef(false);
    useEffect(() => {
        if (!shown.current) {
            shown.current = true;
            showToast.warning(message, {
                duration: 4000,
                progress: true,
                position: "top-right",
                transition: "fadeIn",
                icon: '',
                sound: false,
            });
        }
    }, [message]);


    return null;
};

export const InfoToast: React.FC<Prop> = ({ message }) => {
    const shown = useRef(false);
    useEffect(() => {
        if (!shown.current) {
            shown.current = true;
            showToast.warning(message, {
                duration: 4000,
                progress: true,
                position: "top-right",
                transition: "fadeIn",
                icon: '',
                sound: false,
            });
        }
    }, [message]);


    return null;
};
