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
import org.wso2.carbon.user.core.hash.HashProvider;

import java.util.HashMap;
import java.util.Map;

public class UserStoreMgtDataHolder {

    private static UserStoreMgtDataHolder instance = new UserStoreMgtDataHolder();
    private static final Log log = LogFactory.getLog(UserStoreMgtDataHolder.class);
    private CryptoService cryptoService;
    private Map<String, HashProvider> hashProviderMap;

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

    public void setHashProvider(HashProvider hashProvider) {

        if (hashProviderMap == null) {
            hashProviderMap = new HashMap<>();
            hashProviderMap.put(hashProvider.getAlgorithm(), hashProvider);
        } else {
            hashProviderMap.put(hashProvider.getAlgorithm(), hashProvider);
        }
    }

    public HashProvider getHashProvider(String algorithm) {

        return hashProviderMap.get(algorithm);

    }

    public void unbindHashProvider(HashProvider hashProvider) {
        hashProviderMap.remove(hashProvider.getAlgorithm());
    }



}
