package de.bund.bsi.tresor.xaip.validator.api.entity;

import java.io.OutputStream;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author wolffs
 */
@Value
@AllArgsConstructor
public class LoggerConfig
{
    private OutputStream output;
    private boolean      verbose;
}
