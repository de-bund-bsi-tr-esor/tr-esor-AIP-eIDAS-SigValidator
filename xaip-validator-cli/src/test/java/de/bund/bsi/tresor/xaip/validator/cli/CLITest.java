package de.bund.bsi.tresor.xaip.validator.cli;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toMap;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;

/**
 * @author wolffs
 */
public class CLITest
{
    public static final String TEST_KEY_1   = "test.key1";
    public static final String TEST_VALUE_1 = "testValue1";
    
    public static final String TEST_KEY_2   = "test.key2";
    public static final String TEST_VALUE_2 = "testValue2";
    
    private Arguments          args;
    
    @BeforeEach
    public void setup()
    {
        Map<String, String> moduleConfig = new HashMap<>();
        moduleConfig.put( TEST_KEY_1, TEST_VALUE_1 );
        
        args = new Arguments();
        args.setModuleConfig( moduleConfig );
    }
    
    /**
     * Testing if the external configuration is being properly merged with the module configuration from the command line. The priority is
     * cmdArgs(args.getModuleConfig) > confArgs(args.getConfig)
     * 
     * @return the dynamic tests
     * @throws Exception
     *             when a test fails
     */
    @TestFactory
    Stream<DynamicTest> mergeConfigTest() throws Exception
    {
        return Stream.of(
                dynamicTest( "should add external config",
                        () -> useExternalConfig( args, "case_partial.properties", Set.of( TEST_KEY_2 ) ) ),
                dynamicTest( "should not priorize external config",
                        () -> useExternalConfig( args, "case_complete.properties", emptySet() ) ),
                dynamicTest( "should not throw on empty external config",
                        () -> useExternalConfig( args, "case_empty.properties", emptySet() ) ) );
    }
    
    public void useExternalConfig( Arguments arguments, String externalConfig, Set<String> expectedDiff ) throws URISyntaxException
    {
        arguments.setConfig( Paths.get( CLITest.class.getResource( "/cli/" + externalConfig ).toURI() ) );
        
        Map<String, String> before = arguments.getModuleConfig();
        assertDoesNotThrow( () -> CLI.mergeConfig( arguments ) );
        
        Map<String, String> after = arguments.getModuleConfig();
        
        Map<String, String> actualDiff = after.entrySet().stream()
                .filter( entryA -> before.entrySet().stream()
                        .noneMatch( entryB -> entryA.getKey().equals( entryB.getKey() )
                                && entryA.getValue().equals( entryB.getValue() ) ) )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
        
        assertThat( actualDiff.keySet(), hasItems( expectedDiff.toArray( String[]::new ) ) );
    }
}
