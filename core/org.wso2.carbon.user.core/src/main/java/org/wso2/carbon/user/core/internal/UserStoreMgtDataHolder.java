/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.user.core.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.crypto.api.CryptoService;
import org.wso2.carbon.user.core.hash.HashCalculator;

import java.util.HashMap;
import java.util.Map;

public class UserStoreMgtDataHolder {

    private static UserStoreMgtDataHolder instance = new UserStoreMgtDataHolder();
    private static final Log log = LogFactory.getLog(UserStoreMgtDataHolder.class);
    private CryptoService cryptoService;
    private Map<String,HashCalculator> hashCalculatorMap;

    public static UserStoreMgtDataHolder getInstance() {

        return instance;
    }

    private UserStoreMgtDataHolder() {

    }

    public void setCryptoService(CryptoService cryptoService) {

        this.cryptoService = cryptoService;
    }

    public CryptoService getCryptoService() {

        return cryptoService;
    }

    public void setHashCalculator(HashCalculator hashCalculator) {

        if (hashCalculatorMap == null) {
            hashCalculatorMap = new HashMap<>();
            hashCalculatorMap.put(hashCalculator.getAlgorithm(),hashCalculator);
        } else {
            hashCalculatorMap.put(hashCalculator.getAlgorithm(), hashCalculator);
        }
    }

    public HashCalculator getHashCalculator(String algorithm) {

        HashCalculator hashCalculator = hashCalculatorMap.get(algorithm);
        return hashCalculator;
    }

    public void unbindHashCalculator(HashCalculator hashCalculator){
        hashCalculatorMap.remove(hashCalculator.getAlgorithm());
    }



}
