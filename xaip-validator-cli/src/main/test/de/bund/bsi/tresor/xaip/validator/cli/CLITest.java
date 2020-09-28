package de.bund.bsi.tresor.xaip.validator.cli;

import static java.util.stream.Collectors.toMap;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;

/**
 * @author wolffs
 */
public class CLITest
{
    private Arguments          args;
    
    public static final String TEST_KEY_1   = "test.key";
    public static final String TEST_VALUE_1 = "testValue";
    
    public static final String TEST_KEY_2   = "test.key";
    public static final String TEST_VALUE_2 = "testValue";
    
    @BeforeEach
    public void setup()
    {
        Map<String, String> moduleConfig = new HashMap<>();
        moduleConfig.put( TEST_KEY_1, TEST_VALUE_1 );
        moduleConfig.put( TEST_KEY_2, TEST_VALUE_2 );
        
        args = new Arguments();
        args.setModuleConfig( moduleConfig );
    }
    
    @TestFactory
    Stream<DynamicTest> mergeConfigTest() throws Exception
    {
        Arguments args = new Arguments();
        Map<String, String> empty = new HashMap<>();
        Map<String, String> partial = new HashMap<>();
        Map<String, String> complete = new HashMap<>();
        
        return Stream.of(
                dynamicTest( "should use partial external config", () -> useExternalConfig( args, partial, Set.of( TEST_KEY_1 ) ) ),
                dynamicTest( "should use complete external config",
                        () -> useExternalConfig( args, complete, args.getModuleConfig().keySet() ) ),
                dynamicTest( "should use complete external config",
                        () -> useExternalConfig( args, empty, args.getModuleConfig().keySet() ) ) );
    }
    
    public void useExternalConfig( Arguments arguments, Map<String, String> externalConfig, Set<String> expectedDiff )
    {
        Map<String, String> before = arguments.getModuleConfig();
        
        assertDoesNotThrow( () -> CLI.mergeConfig( arguments ) );
        
        Map<String, String> after = arguments.getModuleConfig();
        
        // TODO key should match but value mismatch
        Map<String, String> actualDiff = after.entrySet().stream()
                .filter( entry -> before.entrySet().stream().noneMatch( entry::equals ) )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
        
        assertThat( actualDiff.keySet(), hasItems( expectedDiff.toArray( String[]::new ) ) );
    }
    
    @Test
    public void externalConfigTest()
    {
        
        // load when
        // existent
        //
        
        // dont load when
        // external config null
        // external config empty
        // external config not readable
    }
    
    public void internalConfigTest()
    {
        // ok
        // not ok
    }
    
    // if ( args.getConfig() != null && Files.isReadable( args.getConfig() ) )
    // {
    // Properties config = new Properties();
    // try ( InputStream configData = Files.newInputStream( args.getConfig() ) )
    // {
    // config.load( configData );
    // config.putAll( args.getModuleConfig() );
    // args.setModuleConfig( (Map) config );
    // }
    // }
    
}
