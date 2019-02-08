package org.freedesktop.dbus.connections.transports;

import java.io.IOException;

import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.BusAddress.AddressBusTypes;
import org.slf4j.LoggerFactory;

/**
 * Factory to create connection to DBus using unix socket or TCP.
 * 
 * @author hypfvieh
 * @since v3.2.0 - 2019-02-08
 */
public final class TransportFactory {
    
    private TransportFactory() {
        
    }
    
    /**
     * Creates a new transport encapsulating connection to a unix socket or TCP socket.
     * @param _address Address parameter
     * @param _timeout timeout in milliseconds
     * @return {@link AbstractTransport}
     * @throws IOException when transport could not be created
     */
    public static AbstractTransport createTransport(BusAddress _address, int _timeout) throws IOException {
        LoggerFactory.getLogger(TransportFactory.class).debug("Connecting to {}", _address);
        
        if (_address.getBusType() == AddressBusTypes.UNIX) {
            return new UnixSockTransport(_address, _timeout);
        } else if (_address.getBusType() == AddressBusTypes.TCP) {
            return new TcpTransport(_address, _timeout);
        } else {
            throw new IOException("Unknown address type " + _address.getType());
        }
    }
}
