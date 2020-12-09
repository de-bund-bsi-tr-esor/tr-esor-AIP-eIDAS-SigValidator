package de.bund.bsi.tresor.xaip.validator.signature.context;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wolffs
 */
@Getter
@AllArgsConstructor
public class DefaultSignatureFinderContext
{
    private Set<String> xmlContextIds = new HashSet<>();
}
