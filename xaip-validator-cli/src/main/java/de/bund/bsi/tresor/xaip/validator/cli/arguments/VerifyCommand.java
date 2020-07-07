package de.bund.bsi.tresor.xaip.validator.cli.arguments;

import java.util.Set;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import de.bund.bsi.tresor.xaip.validator.cli.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.cli.converter.TestConverter;
import lombok.Data;

/**
 * @author wolffs
 */
@Data
@Parameters( separators = "=", commandDescriptionKey = MessageBundle.CLI_USAGE_VERIFY )
public class VerifyCommand
{
    public static final String    NAME    = "verify";
    public static final String    ALIAS   = "v";
    
    @Parameter( order = 4, names = { "-t", "--targets" },
            descriptionKey = MessageBundle.CLI_USAGE_TARGET, converter = TestConverter.class )
    private Set<ValidationTarget> targets = Set.of( ValidationTarget.values() );
}
